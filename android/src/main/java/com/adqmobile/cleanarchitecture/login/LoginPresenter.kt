package com.adqmobile.cleanarchitecture.login

import com.adqmobile.controllers.BasePresenter
import com.adqmobile.controllers.BaseView
import com.adqmobile.controllers.LoginController
import com.adqmobile.controllers.LoginView
import com.adqmobile.controllers.task.CallBack
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.usecases.LoginUseCase

class LoginPresenter(private val useCase: LoginUseCase): BasePresenter, CallBack<LoginResponseEntity> {

    var view: LoginView? = null

    override fun attach(view: BaseView) {
        this.view = view as? LoginView
    }

    fun onClickLoginButton() {
        view?.showProgress()
        LoginController(this.useCase, this)
            .attemptLogin(view?.getEmail(), view?.getPassword())
    }

    override fun onCancel() {

    }

    override fun onFinish(result: LoginResponseEntity?) {
        view?.hideProgress()
    }

    override fun onError(error: String) {
        view?.hideProgress()
        view?.onFail(error)
    }
}