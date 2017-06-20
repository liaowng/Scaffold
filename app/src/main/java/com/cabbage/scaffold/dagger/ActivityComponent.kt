package com.cabbage.scaffold.dagger

import com.cabbage.scaffold.ui.demo.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}