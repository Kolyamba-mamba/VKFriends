package com.example.vkfriends.prividers

import android.os.Handler
import com.example.vkfriends.models.FriendModel
import com.example.vkfriends.presenters.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {
    fun testLoadFriends(hasFriends: Boolean){
        Handler().postDelayed({
            val friendsList: ArrayList<FriendModel> = ArrayList()
            if (hasFriends){
                val friend1 = FriendModel(name = "Иван", surname = "Петров", city = null,
                    isOnline = true, avatar = "https://sm-news.ru/wp-content/uploads/2019/08/31/bonding.jpg")
                val friend2 = FriendModel(name = "Дмитрий", surname = "Гладков", city = "Уфа",
                    isOnline = false, avatar = "https://russianpulse.ru/img/https://ic.pics.livejournal.com/matveychev_oleg/27303223/12161948/12161948_original.jpg")
                val friend3 = FriendModel(name = "Гадя", surname = "Петрович", city = "Сочи",
                    isOnline = true, avatar = "https://img.dni.ru/binaries/v3_photo/104099.jpg")
                val friend4 = FriendModel(name = "Михаил", surname = "Зубенко", city = null,
                    isOnline = true, avatar = "https://s00.yaplakal.com/pics/pics_original/4/2/4/13686424.jpg")

                friendsList.add(friend1)
                friendsList.add(friend2)
                friendsList.add(friend3)
                friendsList.add(friend4)
            }

            presenter.friendsLoaded(friendsList = friendsList)
        },2000)
    }
}