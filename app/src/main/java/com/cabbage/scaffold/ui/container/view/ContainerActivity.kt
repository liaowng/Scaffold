package com.cabbage.scaffold.ui.container.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.cabbage.scaffold.R
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.container.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.daggerLazy
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class ContainerActivity : BaseActivity() {

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.frame) lateinit var frame: FrameLayout

    // This `lazy` is probably an overkill...
    @Inject @ActivityScope lateinit var viewModel: daggerLazy<ContainerViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_container)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()
        viewModel.get().data.observe(this, Observer {  })

        val existing = supportFragmentManager.findFragmentById(R.id.frame) as? CounterFragment
        if (existing == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame, CounterFragment.newInstance(), "counter")
                    .commit()
        }
    }
}