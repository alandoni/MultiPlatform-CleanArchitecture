package com.adqmobile.cleanarchitecture

import com.adqmobile.cleanarchitecture.data.UserRepositoryImpl
import com.adqmobile.domain.repositories.UserRepository
import com.adqmobile.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides fun providesLoginUseCase() : LoginUseCase {
        return LoginUseCase(providesUserRepository())
    }

    @Provides fun providesUserRepository() : UserRepository {
        return UserRepositoryImpl()
    }
}