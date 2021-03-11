package com.aakash.androidmadsampleproject

import android.content.Context
import android.net.ConnectivityManager
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : MultiDexApplication() {

    companion object {
        private lateinit var instance : AppApplication

        fun getInstance(): AppApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initiateMultiDex()
    }

    private fun initiateMultiDex() {
        MultiDex.install(this)
    }


    /**
     * Checking the internet connectivity
     *
     * @return true if the connection is available otherwise false
     */
    fun hasNetworkConnection(): Boolean {
        val activeNetwork =
            (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}