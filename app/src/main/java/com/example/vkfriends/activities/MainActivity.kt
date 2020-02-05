package com.example.vkfriends.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.vkfriends.R
import com.example.vkfriends.presenters.LoginPresenter
import com.example.vkfriends.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import com.vk.api.sdk.utils.VKUtils


class MainActivity : MvpAppCompatActivity(), LoginView {

//    private val TAG: String = MainActivity::class.java.simpleName

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnter.setOnClickListener{
            VK.login(this@MainActivity, listOf(VKScope.FRIENDS))
//           loginPresenter.login(isSuccess = true)
        }

//Это получение отпечатка сертификата приложения, нужно было в начале, при настройке
//        val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)
//        Log.e(TAG, "fingerprints ${fingerprints?.get(0)}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!loginPresenter.loginVK(requestCode = requestCode, resultCode = resultCode, data = data)){
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun startLoading() {
        btnEnter.visibility = View.GONE
        pbLogin.visibility = View.VISIBLE
    }

    override fun endLoading() {
        btnEnter.visibility = View.VISIBLE
        pbLogin.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_LONG).show()
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
    }
}
