package com.adqmobile.cleanarchitecture

import com.adqmobile.cleanarchitecture.login.LoginActivity
import com.adqmobile.domain.repositories.user.UserRepository
import com.adqmobile.domain.usecases.LoginUseCase
import dagger.Component

@Component(modules = [AppModule::class, ContextModule::class, UsersModule::class])
interface ApplicationComponent {
    fun inject(activity: LoginActivity)
    fun getLoginUseCase() : LoginUseCase
    fun getUserRepository() : UserRepository
}