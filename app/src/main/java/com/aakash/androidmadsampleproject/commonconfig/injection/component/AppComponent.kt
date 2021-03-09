package com.aakash.androidmadsampleproject.commonconfig.injection.component

import com.aakash.androidmadsampleproject.commonconfig.injection.module.RetrofitModule
import com.aakash.androidmadsampleproject.commonconfig.shareddata.utility.ViewModelModule
import com.aakash.androidmadsampleproject.usermodule.model.UserItemsViewModel
import com.aakash.androidmadsampleproject.usermodule.ui.UserItemsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun inject(activity: UserItemsActivity)
}