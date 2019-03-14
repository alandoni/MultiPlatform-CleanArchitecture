package com.adqmobile.cleanarchitecture

import android.os.Bundle

interface BasePresenter<T: IBaseActivity> {
    fun onCreate(savedInstanceState : Bundle?)
    fun attach(view: T)
}