package com.adqmobile.cleanarchitecture

import android.app.Application
import android.content.Context
import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.presentation.LoginPresenter
import com.adqmobile.domain.repositories.user.UserLocalRepository
import com.adqmobile.domain.repositories.user.UserRemoteRepository
import com.adqmobile.domain.usecases.LoginUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val applicationModule = module {
    factory { Database(get()) }
}

val LoginModule = module {
    factory { LoginPresenter(get()) }
    factory { LoginUseCase(get(), get()) }
    factory { UserLocalRepository(get<Database>()) }
    factory { UserRemoteRepository(HttpRequest<LoginRequestEntity>()) }
}

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(applicationModule, LoginModule)
        }
    }
}