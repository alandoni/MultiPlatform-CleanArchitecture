package com.adqmobile.cleanarchitecture

import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.cleanarchitecture.login.LoginPresenter
import com.adqmobile.data.user.UserLocalRepository
import com.adqmobile.data.user.UserRemoteRepository
import com.adqmobile.domain.repositories.AbstractDatabase
import com.adqmobile.domain.repositories.BaseRequest
import com.adqmobile.domain.usecases.LoginUseCase
import org.koin.dsl.module

val ApplicationModule = module {
    factory { Database(get()) as AbstractDatabase }
    single { HttpRequest() as BaseRequest }
}
val LoginModule = module {
    factory { LoginPresenter(get()) }
    factory { UserLocalRepository(get()) }
    factory { UserRemoteRepository(get()) }
    factory { LoginUseCase(get(), get()) }
}