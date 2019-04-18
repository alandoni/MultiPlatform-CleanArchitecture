package com.adqmobile.domain

import kotlin.test.*
import com.adqmobile.presentation.LoginPresenter
import com.adqmobile.presentation.LoginView

class TestLoginPresenter {

    private val presenter = LoginPresenter(TestUtils.createLoginUseCase())

    @Test
    fun testPresenterSuccess() {
        val view = object: LoginView {
            override fun getEmail(): String {
                return "alan.etm@gmail.com"
            }

            override fun getPassword(): String {
                return "123123"
            }

            override fun onFail(error: String?) {
                assertNotNull(error)
            }

            override fun onSuccess() {
                assertTrue(true)
            }

            override fun showProgress() {
            }

            override fun hideProgress() {
            }
        }
        presenter.attach(view)
        presenter.attemptLogin()
    }
}