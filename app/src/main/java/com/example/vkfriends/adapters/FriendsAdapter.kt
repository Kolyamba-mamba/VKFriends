package com.example.vkfriends.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkfriends.R
import com.example.vkfriends.models.FriendModel

class FriendsAdapter: RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    private var friendsList = emptyList<FriendModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.cell_friend, parent, false))
    }

    override fun getItemCount(): Int = friendsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }
}