package com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint

import com.aakash.androidmadsampleproject.usermodule.dto.UserItemResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoint {

    @GET("users")
    fun  fetchUsers() : Observable<ArrayList<UserItemResponse>>


    @GET("users/{username}")
    fun  fetchUser(@Path("username") name: String) : Observable<UserItemResponse>
}