package com.adqmobile.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T: BasePresenter> : AppCompatActivity(), IBaseActivity {

    @Inject protected lateinit var presenter : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter.attach(this)
        this.presenter.onCreate(savedInstanceState)
    }
}