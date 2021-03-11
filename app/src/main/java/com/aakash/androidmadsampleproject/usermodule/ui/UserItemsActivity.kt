package com.aakash.androidmadsampleproject.usermodule.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aakash.androidmadsampleproject.R
import com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint.ApiEndPoint
import com.aakash.androidmadsampleproject.usermodule.model.UserItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_items.*
import javax.inject.Inject

@AndroidEntryPoint
class UserItemsActivity : ComponentActivity(){

    private val userViewModel: UserItemsViewModel by viewModels()

    @Inject
    lateinit var apiService: ApiEndPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_items)
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