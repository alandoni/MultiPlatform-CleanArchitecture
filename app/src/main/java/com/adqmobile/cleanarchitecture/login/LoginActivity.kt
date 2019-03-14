package com.adqmobile.cleanarchitecture.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.adqmobile.cleanarchitecture.BaseActivity

import com.adqmobile.cleanarchitecture.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity<ILoginActivity, LoginPresenter>(), ILoginActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)

        DaggerLoginComponent.create().inject(this)
        presenter.attach(this)

        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                this.presenter.attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        signInButton.setOnClickListener {
            this.presenter.attemptLogin()
        }

        super.onCreate(savedInstanceState)
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun getEmail(): String {
        return email.text.toString() //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPassword(): String {
        return password.text.toString() //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFail(error: String?) {
        if (error != null) {
            this.error.setText(error)
            this.error.visibility = View.VISIBLE
        } else {
            this.error.visibility = View.GONE
        }
    }

    override fun onSuccess() {
        this.error.visibility = View.GONE
    }

    override fun showProgress() {
        showProgress(true)
    }

    override fun hideProgress() {
        showProgress(false)
    }

}
