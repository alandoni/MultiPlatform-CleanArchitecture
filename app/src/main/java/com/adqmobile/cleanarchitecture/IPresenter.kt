package com.adqmobile.cleanarchitecture

import android.os.Bundle

interface IPresenter {
    fun onCreate(savedInstanceState : Bundle?)
    fun attach(view: IBaseActivity)
}