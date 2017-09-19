package com.cabbage.scaffold.ui.demo

import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import com.cabbage.scaffold.BuildConfig
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.mvp.MvpView
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MvpView {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var rxPermission: RxPermissions

    var clickSub: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("onCreate")
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        activityComponent.inject(this)

        tvVersionName.text = BuildConfig.VERSION_NAME
        tvVersionCode.text = "${BuildConfig.VERSION_CODE}"
    }

    public override fun onStart() {
        super.onStart()
        Timber.v("onStart")
        presenter.mvpView = this
        presenter.ensureLocationPermission(rxPermission)

        clickSub = RxView.clicks(fab).subscribeBy(onNext = { presenter.ensureLocationPermission(rxPermission) })
    }

    public override fun onStop() {
        super.onStop()
        Timber.v("onStop")
        presenter.mvpView = null

        clickSub?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("onDestroy")
    }

    fun showLocationPermissionResult(granted: Boolean) {
        Toast.makeText(this, "Granted: $granted", Toast.LENGTH_SHORT).show()
    }
}
