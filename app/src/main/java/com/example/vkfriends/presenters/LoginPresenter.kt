package com.example.vkfriends.presenters

import android.os.Handler
import com.example.vkfriends.R
import com.example.vkfriends.views.LoginView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter:MvpPresenter<LoginView>() {
    fun login(isSuccess: Boolean){
        viewState.startLoading()
        Handler().postDelayed({
            viewState.endLoading()
            if (isSuccess)
                viewState.openFriends()
            else
                viewState.showError(textResource = R.string.login_error)
        }, 1000)
    }
}