package com.adqmobile.cleanarchitecture

import android.app.Application
import android.content.Context
import com.adqmobile.cleanarchitecture.data.UserRepositoryImpl
import com.adqmobile.domain.repositories.user.UserRepository
import com.adqmobile.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class AppModule(private val application: Application) {

    @Provides fun providesApplication(): Context {
        return application
    }
}