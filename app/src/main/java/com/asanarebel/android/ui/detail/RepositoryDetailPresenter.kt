package com.asanarebel.android.ui.detail

import com.asanarebel.android.data.DataManager
import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import com.asanarebel.android.data.remote.CallbackWrapper
import com.asanarebel.android.ui.base.BasePresenter
import com.asanarebel.android.utils.rx.SchedulerProvider
import javax.inject.Inject

class RepositoryDetailPresenter<V : RepositoryDetailMvpView> @Inject constructor(private val dm: DataManager, val scheduler: SchedulerProvider) : BasePresenter<V>(dm, scheduler), RepositoryDetailMvpPresenter<V> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun onSubscriberRequest(owner: String?, repo: String?) {
        // display progress bar
        getMvpView()!!.showLoading()

        getDataManager()!!.fetchSubscribers(owner, repo)!!.subscribeOn(scheduler.io()).observeOn(scheduler.ui())
                .subscribeWith(object : CallbackWrapper<List<SubscriberResponse>>() {

                    override fun onSuccess(subscriberResponse: List<SubscriberResponse>) {
                        // dismiss the progress bar
                        getMvpView()!!.hideLoading()

                        // send back result to activity
                        getMvpView()!!.onFetchedSubscribers(subscriberResponse)
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
