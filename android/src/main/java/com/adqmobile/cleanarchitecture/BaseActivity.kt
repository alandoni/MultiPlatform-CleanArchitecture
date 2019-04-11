package com.adqmobile.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adqmobile.cleanarchitecture.data.Database
import com.adqmobile.cleanarchitecture.data.HttpRequest
import com.adqmobile.cleanarchitecture.login.LoginActivity
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.presentation.IBaseView
import com.adqmobile.domain.presentation.IPresenter
import javax.inject.Inject

abstract class BaseActivity<T: IPresenter> : AppCompatActivity(), IBaseView {

    @Inject
    protected lateinit var presenter : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerApplicationComponent.builder()
            .usersModule(UsersModule(
                this,
                Database(this),
                HttpRequest()
            ))
            .build()
            .inject(this as LoginActivity)

        this.presenter.attach(this)
    }
}