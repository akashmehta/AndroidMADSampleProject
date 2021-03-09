package com.aakash.androidmadsampleproject.usermodule.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint.ApiEndPoint
import java.lang.IllegalArgumentException

class UserViewModelFactory (private val apiService: ApiEndPoint) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserItemsViewModel::class.java))
            UserItemsViewModel(apiService) as T
        else throw IllegalArgumentException("")
    }
}