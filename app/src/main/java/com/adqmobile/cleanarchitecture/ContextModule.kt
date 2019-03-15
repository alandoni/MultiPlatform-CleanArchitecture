package com.adqmobile.cleanarchitecture

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val activity: Activity, private val context: Context) {

    @Provides fun providesContext() : Context {
        return context
    }

    @Provides fun providesActivity() : Activity {
        return activity
    }
}