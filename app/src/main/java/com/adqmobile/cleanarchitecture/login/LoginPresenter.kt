package com.adqmobile.cleanarchitecture.login

import android.os.Bundle
import com.adqmobile.cleanarchitecture.BasePresenter
import com.adqmobile.cleanarchitecture.task.CallBack
import com.adqmobile.cleanarchitecture.task.Task
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.usecases.LoginUseCase
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<ILoginActivity>, CallBack<LoginResponseEntity> {

    private lateinit var view: ILoginActivity
    @Inject lateinit var loginUseCase: LoginUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
    }

    override fun attach(view: ILoginActivity) {
        this.view = view
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    fun attemptLogin() {
        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        view.showProgress()
        val loginRequest = LoginRequestEntity(view.getEmail(), view.getPassword())
        Task(loginUseCase, this).execute(loginRequest)
    }

    override fun onFinish(result: LoginResponseEntity?) {
        view.hideProgress()
        view.onSuccess()
    }

    override fun onError(error: String) {
        view.hideProgress()
        view.onFail(error)
    }
}