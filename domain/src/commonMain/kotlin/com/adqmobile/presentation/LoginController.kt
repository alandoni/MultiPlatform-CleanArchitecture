package com.adqmobile.presentation

import com.adqmobile.presentation.task.CallBack
import com.adqmobile.presentation.task.Task
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.usecases.LoginUseCase

class LoginController constructor(private val loginUseCase: LoginUseCase,
                                  private val callback: CallBack<LoginResponseEntity>) {

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    fun attemptLogin(email: String?, password: String?) {
        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        val loginRequest = LoginRequestEntity(email!!, password)
        Task(loginUseCase, this.callback).execute(loginRequest)
    }
}