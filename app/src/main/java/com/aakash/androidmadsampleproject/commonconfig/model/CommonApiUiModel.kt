package com.aakash.androidmadsampleproject.commonconfig.model

import android.util.Pair


data class CommonApiUiModel<T>(val progress: Boolean = false,
                               val response: T? = null,
                               val error: Pair<Int, String>? = null)