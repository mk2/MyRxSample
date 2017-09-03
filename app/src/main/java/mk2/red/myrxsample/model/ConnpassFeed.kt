package mk2.red.myrxsample.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 *
 */
open class ConnpassFeed : RealmObject() {

    @PrimaryKey
    open var id: String = UUID.randomUUID().toString()

    open var items: RealmList<ConnpassFeedItem> = RealmList()
}