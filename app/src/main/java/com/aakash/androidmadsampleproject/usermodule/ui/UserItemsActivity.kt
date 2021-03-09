package com.aakash.androidmadsampleproject.usermodule.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aakash.androidmadsampleproject.AppApplication
import com.aakash.androidmadsampleproject.R
import com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint.ApiEndPoint
import com.aakash.androidmadsampleproject.usermodule.dto.UserItemResponse
import com.aakash.androidmadsampleproject.usermodule.model.UserItemsViewModel
import com.aakash.androidmadsampleproject.usermodule.model.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_user_items.*
import javax.inject.Inject

class UserItemsActivity : AppCompatActivity(){

    private lateinit var userViewModel: UserItemsViewModel
    private lateinit var userViewModelFactory: UserViewModelFactory

    @Inject
    lateinit var apiService: ApiEndPoint
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_items)
        (application as AppApplication).appComponent.inject(this)
        userViewModelFactory = UserViewModelFactory(apiService)
        userViewModel = ViewModelProvider(this, userViewModelFactory)[UserItemsViewModel::class.java]
        setupApi()
    }

    private fun setupApi() {
        userViewModel.getLiveData().observe(this, Observer {
            it.response?.let { userItems->

                rvUserItems.layoutManager = LinearLayoutManager(this)
                rvUserItems.adapter = UserItemsListAdapter(userItems)
            }

        })
        userViewModel.fetchUsers()
    }
}