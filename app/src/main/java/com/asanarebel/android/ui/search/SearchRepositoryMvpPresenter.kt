package com.asanarebel.android.ui.search

import com.asanarebel.android.ui.base.MvpPresenter


interface SearchRepositoryMvpPresenter<V : SearchRepositoryMvpView> : MvpPresenter<V> {

    fun onSearchRepositoryClick(query: String)
}