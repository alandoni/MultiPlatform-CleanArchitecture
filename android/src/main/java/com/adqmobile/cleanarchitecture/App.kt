package com.adqmobile.cleanarchitecture

import android.app.Application
import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.domain.presentation.LoginPresenter
import com.adqmobile.domain.repositories.AbstractDatabase
import com.adqmobile.domain.repositories.Request
import com.adqmobile.domain.repositories.user.UserLocalRepository
import com.adqmobile.domain.repositories.user.UserRemoteRepository
import com.adqmobile.domain.usecases.LoginUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val ApplicationModule = module {
    factory { Database(get()) as AbstractDatabase }
    single { HttpRequest() as Request }
}

val LoginModule = module {
    factory { LoginPresenter(get()) }
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