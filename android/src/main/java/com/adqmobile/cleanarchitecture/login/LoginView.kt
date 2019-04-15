package com.adqmobile.controllers

interface LoginView : BaseView {
    fun getEmail() : String
    fun getPassword() : String
    fun onFail(error : String?)
    fun onSuccess()
    fun showProgress()
    fun hideProgress()
}