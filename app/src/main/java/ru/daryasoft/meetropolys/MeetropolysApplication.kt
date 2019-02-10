package ru.daryasoft.meetropolys

import android.app.Application
import io.realm.Realm

class MeetropolysApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}