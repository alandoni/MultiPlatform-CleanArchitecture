package com.adqmobile.domain.presentation

import com.adqmobile.domain.presentation.IBaseView

interface ILoginView : IBaseView {
    fun getEmail() : String
    fun getPassword() : String
    fun onFail(error : String?)
    fun onSuccess()
    fun showProgress()
    fun hideProgress()
}