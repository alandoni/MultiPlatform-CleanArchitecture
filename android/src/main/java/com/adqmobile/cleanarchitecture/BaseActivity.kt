package com.adqmobile.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adqmobile.cleanarchitecture.data.DatabaseHandler
import com.adqmobile.controllers.BaseView
import com.adqmobile.controllers.BasePresenter

abstract class BaseActivity<T: BasePresenter> : AppCompatActivity(), BaseView {

    abstract val presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (DatabaseHandler.requestPermissionIfNeeded(this)) {
            return
        }
        this.presenter.attach(this)
    }
}