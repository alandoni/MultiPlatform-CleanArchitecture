package com.adqmobile.cleanarchitecture

import android.app.Activity
import com.adqmobile.cleanarchitecture.data.UserRepositoryImpl
import com.adqmobile.domain.presentation.LoginPresenter
import com.adqmobile.domain.repositories.user.UserRepository
import com.adqmobile.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class UsersModule(private val activity: Activity) {
    @Provides
    fun providesLoginUseCase(): LoginUseCase {
        return LoginUseCase(providesUserRepository())
    }

    @Provides
    fun providesUserRepository(): UserRepository {
        return UserRepositoryImpl(activity)
    }

    @Provides
    fun providesLoginPresenter(): LoginPresenter {
        return LoginPresenter(providesLoginUseCase())
    }
}