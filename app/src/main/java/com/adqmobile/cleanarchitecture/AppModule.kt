package com.adqmobile.cleanarchitecture

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class AppModule(private val application: Application) {

    @Provides fun providesApplication(): Context {
        return application
    }
}