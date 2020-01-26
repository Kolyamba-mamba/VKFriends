package com.example.vkfriends.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.vkfriends.R
import com.example.vkfriends.presenters.LoginPresenter
import com.example.vkfriends.views.LoginView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnter.setOnClickListener{
           loginPresenter.login(isSuccess = true)
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
