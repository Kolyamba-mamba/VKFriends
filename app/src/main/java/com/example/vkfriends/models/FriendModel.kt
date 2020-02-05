package com.example.vkfriends.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

//class FriendModel(
//    var id: Int,
//    var name: String,
//    var surname: String,
//    var city: String?,
//    var avatar: String?,
//    var isOnline: Boolean
//)

data class FriendModel(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val city: String? = "",
    val avatar: String = "",
    val isOnline: Boolean
) : Parcelable {

    @SuppressLint("NewApi")
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readBoolean()
    )

    @SuppressLint("NewApi")
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(city)
        parcel.writeString(avatar)
        parcel.writeBoolean(isOnline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FriendModel> {
        override fun createFromParcel(parcel: Parcel): FriendModel {
            return FriendModel(parcel)
        }

        override fun newArray(size: Int): Array<FriendModel?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject)
                = FriendModel(id = json.optInt("id", 0),
            name = json.optString("first_name", ""),
            surname = json.optString("last_name", ""),
            avatar = json.optString("photo_100", ""),
            city = json.optString("city", ""),
            isOnline = json.optBoolean("online",false))
    }
}