package com.example.vkfriends.presenters

import android.content.Intent
import android.os.Handler
import com.example.vkfriends.R
import com.example.vkfriends.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter:MvpPresenter<LoginView>() {
    //для теста
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

    fun loginVK(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (!VK.onActivityResult(requestCode, resultCode, data, object : VKAuthCallback {
                override fun onLogin(token: VKAccessToken) {
                    viewState.openFriends()
                }

                override fun onLoginFailed(errorCode: Int) {
                    viewState.showError(textResource = R.string.login_error)
                }
            })) {
            return false
        }
        return true
    }
}
