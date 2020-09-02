package com.fadineg.trainingproject

import io.realm.Realm

class RealmManager {
    var realm: Realm? = null

    fun setInstance(){
        realm = Realm.getDefaultInstance()
    }

    fun closeRealm(){
        realm?.close()
    }
}