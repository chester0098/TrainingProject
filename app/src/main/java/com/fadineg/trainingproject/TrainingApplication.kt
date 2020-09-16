package com.fadineg.trainingproject

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.realm.Realm

class TrainingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        AndroidThreeTen.init(this)
    }
}