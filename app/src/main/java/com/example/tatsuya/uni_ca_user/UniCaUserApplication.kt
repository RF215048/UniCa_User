package com.example.tatsuya.uni_ca_user

import android.app.Application
import io.realm.Realm

class UniCaUserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}