package com.asanarebel.android.ui.base

import com.asanarebel.android.data.DataManager
import com.asanarebel.android.utils.rx.SchedulerProvider


open class BasePresenter<V : MvpView> constructor(private val dm: DataManager, scheduler: SchedulerProvider) : MvpPresenter<V> {

    private var mMvpView: V? = null

    fun getDataManager(): DataManager? = dm

    override fun onDetach() {
        mMvpView = null
    }

    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    fun getMvpView(): V? = mMvpView

    override fun handleApiError(error: Any) {
    }

}


