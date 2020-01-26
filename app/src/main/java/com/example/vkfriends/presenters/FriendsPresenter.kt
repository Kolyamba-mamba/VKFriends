package com.example.vkfriends.presenters

import com.example.vkfriends.R
import com.example.vkfriends.models.FriendModel
import com.example.vkfriends.prividers.FriendsProvider
import com.example.vkfriends.views.FriendsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FriendsPresenter:MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).testLoadFriends(true)
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>){
        viewState.endLoading()
        if (friendsList.isEmpty()){
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_items)
        }
        else
            viewState.setupFritndsList(friendsList = friendsList)
    }
}