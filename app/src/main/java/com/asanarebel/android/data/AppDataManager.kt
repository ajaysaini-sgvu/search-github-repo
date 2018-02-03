package com.asanarebel.android.data

import android.content.Context
import com.asanarebel.android.data.model.search.SearchRepositoryResponse
import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import com.asanarebel.android.data.pref.PreferencesHelper
import com.asanarebel.android.data.remote.ApiHelper
import io.reactivex.Observable
import javax.inject.Inject

open class AppDataManager @Inject constructor(private val preferencesHelper: PreferencesHelper,
                                              private val apiHelper: ApiHelper,
                                              private val context: Context) : DataManager {


    override fun searchRepo(repositoryName: String): Observable<SearchRepositoryResponse>? = apiHelper.searchRepo(repositoryName)

    override fun fetchSubscribers(owner: String?, repo: String?): Observable<List<SubscriberResponse>>? = apiHelper.fetchSubscribers(owner, repo)

}