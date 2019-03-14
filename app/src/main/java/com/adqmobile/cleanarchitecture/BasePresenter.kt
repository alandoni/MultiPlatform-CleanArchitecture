package com.adqmobile.cleanarchitecture

import android.os.Bundle

interface BasePresenter {
    fun onCreate(savedInstanceState : Bundle?)
    fun attach(view: Any)
}