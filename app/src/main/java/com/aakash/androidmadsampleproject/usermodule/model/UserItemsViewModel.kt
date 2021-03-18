package com.aakash.androidmadsampleproject.usermodule.model

import android.os.Build
import android.util.Pair
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aakash.androidmadsampleproject.AppApplication
import com.aakash.androidmadsampleproject.commonconfig.model.CommonApiUiModel
import com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint.ApiEndPoint
import com.aakash.androidmadsampleproject.commonconfig.shareddata.utility.Constants
import com.aakash.androidmadsampleproject.commonconfig.shareddata.utility.singleArgViewModel
import com.aakash.androidmadsampleproject.usermodule.dto.UserItemResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class UserItemsViewModel(var apiService: ApiEndPoint) : ViewModel(), LifecycleObserver {

    companion object {
        val FACTORY =
            singleArgViewModel(
                ::UserItemsViewModel
            )
    }

    private val compositeDisposable = CompositeDisposable()
    private val liveData = MutableLiveData<CommonApiUiModel<ArrayList<UserItemResponse>>>()

    fun getLiveData()  = liveData

    fun fetchUsers() {
        if (AppApplication.getInstance().hasNetworkConnection()) {
            liveData.value = CommonApiUiModel(progress = true)

            val disposable = apiService.fetchUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap { itemList ->
                    apiService.fetchUser(itemList[0].login ?: "")
                        .materialize()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    item.value?.let {
                        val value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            Objects.toString(it)
                        } else {
                            println("")
                        }
                        println(value)
                    }
//                    liveData.value = CommonApiUiModel(response = itemList)
                }, {
                    liveData.value = CommonApiUiModel(error = Pair(-1,""))
                })
            compositeDisposable.add(disposable)
        } else {
            liveData.value = CommonApiUiModel(error = Pair(Constants.NO_INTERNET, ""))
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}