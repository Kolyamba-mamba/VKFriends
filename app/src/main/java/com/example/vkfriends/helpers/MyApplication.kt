package com.example.vkfriends.helpers

import android.app.Application
import com.vk.api.sdk.VK

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        VK.initialize(applicationContext)
    }
}