package mk2.red.myrxsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_simple.*
import mk2.red.myrxsample.R
import mk2.red.myrxsample.errorLog
import mk2.red.myrxsample.model.ConnpassFeedItem
import mk2.red.myrxsample.service.APIService
import mk2.red.myrxsample.verboseLog

class SimpleActivity : AppCompatActivity() {

    var realm: Realm? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        realm = Realm.getDefaultInstance()

        start_button.setOnClickListener {
            APIService.fetchConnpassFeed().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<ConnpassFeedItem> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: ConnpassFeedItem) {
                            verboseLog { t.link }
                        }

                        override fun onError(e: Throwable) {
                            errorLog { e.message ?: "empty error messege" }
                        }

                        override fun onComplete() {
                        }
                    })
        }
    }
}
