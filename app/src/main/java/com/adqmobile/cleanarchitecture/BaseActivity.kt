package com.adqmobile.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adqmobile.cleanarchitecture.login.LoginActivity
import javax.inject.Inject

abstract class BaseActivity<T: BasePresenter> : AppCompatActivity(), IBaseActivity {

    @Inject protected lateinit var presenter : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this, this.application))
            .usersModule(UsersModule(this))
            .build()
            .inject(this as LoginActivity)

        this.presenter.attach(this)
        this.presenter.onCreate(savedInstanceState)
    }
}