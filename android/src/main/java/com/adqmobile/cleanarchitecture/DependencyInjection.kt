package com.adqmobile.cleanarchitecture

import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.data.base.AbstractDatabase
import com.adqmobile.data.base.BaseRequest
import com.adqmobile.data.user.UserLocalRepository
import com.adqmobile.data.user.UserLocalRepositoryImpl
import com.adqmobile.data.user.UserRemoteRepository
import com.adqmobile.data.user.UserRemoteRepositoryImpl
import com.adqmobile.domain.usecases.LoginUseCase
import com.adqmobile.presentation.LoginPresenter
import org.koin.dsl.module

val ApplicationModule = module {
    factory { Database(get()) as AbstractDatabase }
    single { HttpRequest() as BaseRequest }
}

val LoginModule = module {
    factory { LoginUseCase(get(), get()) }
    factory { LoginPresenter(get()) }
    factory { UserLocalRepositoryImpl(get()) as UserLocalRepository }
    factory { UserRemoteRepositoryImpl(get()) as UserRemoteRepository }
}