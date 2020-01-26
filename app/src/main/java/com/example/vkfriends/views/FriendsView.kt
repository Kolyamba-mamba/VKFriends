package com.example.vkfriends.views

import com.example.vkfriends.models.FriendModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView: MvpView {
    fun startLoading()
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFritndsList(friendsList: ArrayList<FriendModel>)
    fun endLoading()
}