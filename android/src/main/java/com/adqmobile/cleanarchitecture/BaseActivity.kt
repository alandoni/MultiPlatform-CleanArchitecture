package com.adqmobile.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adqmobile.cleanarchitecture.data.DatabaseHandler
import com.adqmobile.domain.presentation.IBaseView
import com.adqmobile.domain.presentation.IPresenter

abstract class BaseActivity<T: IPresenter> : AppCompatActivity(), IBaseView {

    abstract val presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (DatabaseHandler.requestPermissionIfNeeded(this)) {
            return
        }
        this.presenter.attach(this)
    }
}