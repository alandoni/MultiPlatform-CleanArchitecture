package com.adqmobile.cleanarchitecture.login

import com.adqmobile.cleanarchitecture.AppModule
import dagger.Component

@Component(modules = [AppModule::class])
interface LoginComponent {
    fun inject(app: LoginActivity)
}