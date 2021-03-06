package com.adqmobile.presentation

import com.adqmobile.presentation.task.CallBack
import com.adqmobile.presentation.task.Task
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.usecases.LoginUseCase

class LoginPresenter constructor(private val loginUseCase: LoginUseCase) : BasePresenter, CallBack<LoginResponseEntity> {

    private lateinit var view: LoginView

    override fun attach(view: BaseView) {
        this.view = view as LoginView
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
        view.onFail(result!!.error)
        view.onSuccess()
    }

    override fun onError(error: String) {
        view.hideProgress()
        view.onFail(error)
    }
}