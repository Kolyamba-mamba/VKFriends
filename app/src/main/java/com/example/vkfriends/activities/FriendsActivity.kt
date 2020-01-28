package com.example.vkfriends.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.vkfriends.R
import com.example.vkfriends.adapters.FriendsAdapter
import com.example.vkfriends.models.FriendModel
import com.example.vkfriends.presenters.FriendsPresenter
import com.example.vkfriends.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    private lateinit var mAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        friendsPresenter.loadFriends()

        mAdapter = FriendsAdapter()

        etSearch.addTextChangedListener ( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mAdapter.filter(s.toString())
            }
        } )

        rvFriends.adapter = mAdapter
        rvFriends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL,false)
        rvFriends.setHasFixedSize(true)
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

        mAdapter.setupFriends(friendsList = friendsList)
    }

}
