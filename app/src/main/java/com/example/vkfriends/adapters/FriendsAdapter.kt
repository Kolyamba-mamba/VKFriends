package com.example.vkfriends.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkfriends.R
import com.example.vkfriends.models.FriendModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_friend.view.*

class FriendsAdapter: RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    private var mFriendsList: ArrayList<FriendModel> = ArrayList()
    private var mSourceList:ArrayList<FriendModel> = ArrayList()

    fun setupFriends(friendsList: List<FriendModel>){
        mSourceList.clear()
        mSourceList.addAll(friendsList)
        filter(query = "")

    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = false)|| it.surname.contains(query, ignoreCase = false)){
                mFriendsList.add(it)
            } else {
                it.city?.let { city -> if (city.contains(query, ignoreCase = false)){
                    mFriendsList.add(it)
                }}
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.cell_friend, parent, false))
    }

    override fun getItemCount(): Int {
        println(mFriendsList.size)
        return mFriendsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mFriendsList[position])
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val civAvatar = view.profile_image
        val name = view.tvName
        val tvCity = view.tvCity
        val ivOnline = view.ivOnline

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendModel){
            friendModel.avatar?.let { url -> Picasso.get().load(url).into(civAvatar)}

            name.text = "${friendModel.name} ${friendModel.surname}"
            tvCity.text = itemView.context.getString(R.string.friend_no_city)
            friendModel.city?.let { city -> tvCity.text = city }

            if (friendModel.isOnline)
                ivOnline.visibility = View.VISIBLE
            else
                ivOnline.visibility = View.GONE

        }

    }
}