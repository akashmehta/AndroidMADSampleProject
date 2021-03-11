package com.aakash.androidmadsampleproject.usermodule.model

import android.util.Pair
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aakash.androidmadsampleproject.AppApplication
import com.aakash.androidmadsampleproject.commonconfig.model.CommonApiUiModel
import com.aakash.androidmadsampleproject.commonconfig.shareddata.endpoint.ApiEndPoint
import com.aakash.androidmadsampleproject.commonconfig.shareddata.utility.Constants
import com.aakash.androidmadsampleproject.commonconfig.shareddata.utility.standardObservable
import com.aakash.androidmadsampleproject.usermodule.dto.UserItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class UserItemsViewModel @Inject constructor(private val apiService: ApiEndPoint): ViewModel(), LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()
    private val liveData = MutableLiveData<CommonApiUiModel<ArrayList<UserItemResponse>>>()

    fun getLiveData()  = liveData

    fun fetchUsers() {
        if (AppApplication.getInstance().hasNetworkConnection()) {
            liveData.value = CommonApiUiModel(progress = true)

            val disposable = apiService.fetchUsers()
                .standardObservable()
                .subscribe({ itemList ->
                    liveData.value = CommonApiUiModel(response = itemList)
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