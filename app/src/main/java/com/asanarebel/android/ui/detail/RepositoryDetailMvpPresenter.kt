package com.asanarebel.android.ui.detail

import com.asanarebel.android.ui.base.MvpPresenter


interface RepositoryDetailMvpPresenter<V : RepositoryDetailMvpView> : MvpPresenter<V> {

    fun onSubscriberRequest(owner: String?, repo: String?)
}