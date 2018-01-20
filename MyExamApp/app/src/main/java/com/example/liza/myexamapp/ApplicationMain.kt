package com.example.liza.myexamapp

import android.app.Application
import android.content.Context

class ApplicationMain : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
