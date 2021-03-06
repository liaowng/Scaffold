package com.cabbage.scaffold.ui.main.di

import android.app.Activity
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.dagger.RxPermissionModule
import com.cabbage.scaffold.ui.main.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [RxPermissionModule::class])
abstract class MainActivityModule {

    @Binds @ActivityScope
    abstract fun bindsActivity(act: MainActivity): Activity
}