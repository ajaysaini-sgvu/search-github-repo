package com.asanarebel.android.ui.search

import com.asanarebel.android.data.model.search.SearchRepositoryResponse
import com.asanarebel.android.ui.base.MvpView

interface SearchRepositoryMvpView : MvpView {
    fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse)
}