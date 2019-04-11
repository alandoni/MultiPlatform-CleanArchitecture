package com.adqmobile.cleanarchitecture

import android.app.Activity
import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.presentation.LoginPresenter
import com.adqmobile.domain.repositories.user.UserLocalRepository
import com.adqmobile.domain.repositories.user.UserRemoteRepository
import com.adqmobile.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module(includes = [ContextModule::class])
class UsersModule @Inject constructor(
    private val activity: Activity,
    private val database: Database,
    private val httpRequest: HttpRequest<LoginRequestEntity>) {

    @Provides
    fun providesLoginUseCase(): LoginUseCase {
        return LoginUseCase(providesUserLocalRepository(), providesUserRemoteRepository())
    }

    @Provides
    fun providesUserLocalRepository(): UserLocalRepository {
        return UserLocalRepository(database)
    }

    @Provides
    fun providesUserRemoteRepository(): UserRemoteRepository {
        return UserRemoteRepository(httpRequest)
    }

    @Provides
    fun providesLoginPresenter(): LoginPresenter {
        return LoginPresenter(providesLoginUseCase())
    }
}