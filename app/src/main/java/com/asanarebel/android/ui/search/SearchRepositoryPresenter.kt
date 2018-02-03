package com.asanarebel.android.ui.search

import com.asanarebel.android.data.DataManager
import com.asanarebel.android.data.model.search.SearchRepositoryResponse
import com.asanarebel.android.data.remote.CallbackWrapper
import com.asanarebel.android.ui.base.BasePresenter
import com.asanarebel.android.utils.rx.SchedulerProvider
import javax.inject.Inject

class SearchRepositoryPresenter<V : SearchRepositoryMvpView> @Inject constructor(private val dm: DataManager, val scheduler: SchedulerProvider) : BasePresenter<V>(dm, scheduler), SearchRepositoryMvpPresenter<V> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun onSearchRepositoryClick(query: String) {

        // display progress bar
        getMvpView()!!.showLoading()

        getDataManager()!!.searchRepo(query)!!.subscribeOn(scheduler.io()).observeOn(scheduler.ui())
                .subscribeWith(object : CallbackWrapper<SearchRepositoryResponse>() {

                    override fun onSuccess(searchRepositoryResponse: SearchRepositoryResponse) {
                        // dismiss the progress bar
                        getMvpView()!!.hideLoading()

                        // send back result to activity
                        getMvpView()!!.onFetchedRepositories(searchRepositoryResponse)
                    }

                    override fun onError(error: Any) {
                        // dismiss the progress bar
                        getMvpView()!!.hideLoading()

                        // error handling
                        handleApiError(error)
                    }
                })
    }
}