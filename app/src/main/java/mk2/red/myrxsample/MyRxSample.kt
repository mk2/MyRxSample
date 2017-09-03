package mk2.red.myrxsample

import android.app.Application
import android.util.Log
import io.realm.Realm

class MyRxSample : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
    }
}


@Suppress("unused")
inline fun Any.verboseLog(thunk: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.v(this.javaClass.name, thunk());
    }
}

@Suppress("unused")
inline fun Any.errorLog(thunk: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.e(this.javaClass.name, thunk());
    }
}
