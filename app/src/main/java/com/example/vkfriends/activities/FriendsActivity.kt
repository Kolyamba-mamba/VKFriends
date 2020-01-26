package com.example.vkfriends.activities

import android.os.Bundle
import android.view.View
import com.example.vkfriends.R
import com.example.vkfriends.models.FriendModel
import com.example.vkfriends.presenters.FriendsPresenter
import com.example.vkfriends.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FriendsActivity : MvpAppCompatActivity(), FriendsView {


    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        friendsPresenter.loadFriends()
    }

    override fun startLoading() {
        pbFriends.visibility = View.VISIBLE
        etSearch.visibility = View.GONE
        rvFriends.visibility = View.GONE
        tvFriendsError.visibility = View.GONE
    }

    override fun endLoading() {
        pbFriends.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        tvFriendsError.text = getString(textResource)
    }

    override fun setupEmptyList() {
        etSearch.visibility = View.GONE
        rvFriends.visibility = View.GONE
        tvFriendsError.visibility = View.VISIBLE
    }

    override fun setupFritndsList(friendsList: ArrayList<FriendModel>) {
        etSearch.visibility = View.VISIBLE
        rvFriends.visibility = View.VISIBLE
        tvFriendsError.visibility = View.GONE
    }

}
