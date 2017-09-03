package mk2.red.myrxsample.service

import com.inaka.killertask.KillerTask
import mk2.red.myrxsample.model.ConnpassFeedItem
import mk2.red.myrxsample.verboseLog
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

object APIService {

    private val client = OkHttpClient()

    private const val ConnpassFeedUrl = "https://connpass.com/explore/ja.atom"

    fun fetchConnpassFeed(onSuccess: (List<ConnpassFeedItem>) -> Unit, onFailure: (Exception?) -> Unit) {
        val doWork: () -> List<ConnpassFeedItem> = {
            val req = Request.Builder().url(ConnpassFeedUrl).build()
            val res = client.newCall(req).execute()
            val factory = XmlPullParserFactory.newInstance();
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(res.body()?.byteStream(), "UTF-8")
            var eventType = parser.eventType
            val items = ArrayList<ConnpassFeedItem>()
            var item = ConnpassFeedItem()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_DOCUMENT -> verboseLog { "Start Document" }
                    XmlPullParser.START_TAG -> {
                        when (parser.name) {
                            "id" -> item.feedId = parser.nextText()
                            "title" -> item.title = parser.nextText()
                            "link" -> item.link = parser.getAttributeValue(null, "href")
                            "published" -> item.published = parser.nextText()
                            "updated" -> item.updated = parser.nextText()
                            "summary" -> {
                                item.summaryType = parser.getAttributeValue(null, "type")
                                item.summary = parser.nextText()
                            }
                        }
                    }
                    else -> verboseLog { "Unknown event type" }
                }
                items.add(item)
                item = ConnpassFeedItem()
                eventType = parser.next()
            }
            items
        }
        KillerTask(doWork, onSuccess, onFailure).go()
    }
}