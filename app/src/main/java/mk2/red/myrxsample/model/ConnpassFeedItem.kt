package mk2.red.myrxsample.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ConnpassFeedItem : RealmObject() {

    @PrimaryKey
    open var id: String = UUID.randomUUID().toString()

    open var feedId: String = ""
    open var title: String = ""
    open var link: String = ""
    open var published: String = ""
    open var updated: String = ""
    open var summary: String = ""
    open var summaryType: String = ""
}