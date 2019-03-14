package com.adqmobile.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<U: IBaseActivity, T: BasePresenter<U>> : AppCompatActivity(), IBaseActivity {

    @Inject protected lateinit var presenter : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.presenter.onCreate(savedInstanceState)
    }
}