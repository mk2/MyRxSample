package mk2.red.myrxsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_simple.*
import mk2.red.myrxsample.R
import mk2.red.myrxsample.errorLog
import mk2.red.myrxsample.service.APIService
import mk2.red.myrxsample.verboseLog

class SimpleActivity : AppCompatActivity() {

    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        realm = Realm.getDefaultInstance()

        start_button.setOnClickListener {
            APIService.fetchConnpassFeed({ items ->
                verboseLog {
                    val sb = StringBuilder()
                    for (item in items) {
                        sb.append(item.title + ",")
                    }
                    sb.toString()
                }
            }, { e ->
                errorLog { e?.message!! }
            })
        }
    }
}
