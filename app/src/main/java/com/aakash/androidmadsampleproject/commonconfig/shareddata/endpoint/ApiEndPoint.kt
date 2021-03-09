package com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint

import com.aakash.androidmadsampleproject.usermodule.dto.UserItemResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("users")
    fun  fetchUsers() : Single<ArrayList<UserItemResponse>>
}