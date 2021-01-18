package com.fadineg.trainingproject

import android.app.Application
import com.fadineg.trainingproject.di.AppComponent
import com.fadineg.trainingproject.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import io.realm.Realm

class TrainingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        AndroidThreeTen.init(this)
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}