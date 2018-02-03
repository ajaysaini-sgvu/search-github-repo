package com.asanarebel.android.ui.detail

import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import com.asanarebel.android.ui.base.MvpView


interface RepositoryDetailMvpView : MvpView {
    fun onFetchedSubscribers(subscriberResponse: List<SubscriberResponse>)
}