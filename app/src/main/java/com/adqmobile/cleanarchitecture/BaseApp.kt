package com.adqmobile.cleanarchitecture

import android.app.Application
import com.adqmobile.cleanarchitecture.login.DaggerLoginComponent
import com.adqmobile.cleanarchitecture.login.LoginComponent

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerLoginComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent : LoginComponent
    }
}