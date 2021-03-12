package com.aakash.androidmadsampleproject.commonconfig.shareddata.utility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun <T: ViewModel, A> singleArgViewModel(constructor: (A) -> T): (A) -> ViewModelProvider.NewInstanceFactory {
    return { arg : A ->
        object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <V : ViewModel?> create(modelClass: Class<V>): V {
                return constructor(arg) as V
            }
        }
    }
}