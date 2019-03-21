package com.adqmobile.cleanarchitecture.login

import com.adqmobile.cleanarchitecture.IBaseActivity

interface ILoginActivity : IBaseActivity {
    fun getEmail() : String
    fun getPassword() : String
    fun onFail(error : String?)
    fun onSuccess()
    fun showProgress()
    fun hideProgress()
}