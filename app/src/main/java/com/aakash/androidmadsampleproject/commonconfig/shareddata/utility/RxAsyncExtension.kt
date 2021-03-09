package com.aakash.androidmadsampleproject.commonconfig.shareddata.utility

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> Single<T>.standardObservable(): Single<T> = this.subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
