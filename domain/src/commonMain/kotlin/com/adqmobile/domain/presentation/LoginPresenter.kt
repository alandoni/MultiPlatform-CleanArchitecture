package com.adqmobile.domain.presentation

import com.adqmobile.domain.presentation.IBaseView
import com.adqmobile.domain.presentation.task.CallBack
import com.adqmobile.domain.presentation.task.Task
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.presentation.ILoginView
import com.adqmobile.domain.usecases.LoginUseCase
import com.adqmobile.domain.presentation.IPresenter

class LoginPresenter constructor(private val loginUseCase: LoginUseCase) : IPresenter, CallBack<LoginResponseEntity> {

    private lateinit var view: ILoginView

    override fun attach(view: IBaseView) {
        this.view = view as ILoginView
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