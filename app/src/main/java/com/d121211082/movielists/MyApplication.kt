package com.d121211082.movielists

import android.app.Application
import com.d121211082.movielists.data.AppCointainer
import com.d121211082.movielists.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppCointainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}