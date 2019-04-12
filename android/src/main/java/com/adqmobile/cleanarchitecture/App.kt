package com.adqmobile.cleanarchitecture

import android.app.Application
import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.presentation.LoginController
import com.adqmobile.domain.repositories.AbstractDatabase
import com.adqmobile.domain.repositories.BaseRequest
import com.adqmobile.data.user.UserLocalRepository
import com.adqmobile.data.user.UserRemoteRepository
import com.adqmobile.domain.usecases.LoginUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val ApplicationModule = module {
    factory { Database(get()) as AbstractDatabase }
    single { HttpRequest() as BaseRequest }
}

val LoginModule = module {
    factory { LoginController(get()) }
    factory { UserLocalRepository(get()) }
    factory { UserRemoteRepository(get()) }
    factory { LoginUseCase(get(), get()) }
}

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(ApplicationModule, LoginModule)
        }
    }
}