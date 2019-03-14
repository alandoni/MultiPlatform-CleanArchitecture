package com.adqmobile.cleanarchitecture

import android.content.Context
import com.adqmobile.cleanarchitecture.data.UserRepositoryImpl
import com.adqmobile.domain.repositories.user.UserRepository
import com.adqmobile.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BaseApp) {

    @Provides fun providesLoginUseCase() : LoginUseCase {
        return LoginUseCase(providesUserRepository())
    }

    @Provides fun providesUserRepository() : UserRepository {
        return UserRepositoryImpl(providesContext())
    }

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }
}