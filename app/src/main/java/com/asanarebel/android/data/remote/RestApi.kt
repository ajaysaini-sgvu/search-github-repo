/*
 * Copyright 2017 Ajay Saini
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asanarebel.android.data.remote

import com.asanarebel.android.data.model.search.SearchRepositoryResponse
import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("/search/repositories")
    fun searchRepo(@Query("q") query: String): Observable<SearchRepositoryResponse>

    @GET("/repos/{owner}/{repo}/subscribers")
    fun fetchSubscribers(@Path("owner") owner: String?, @Path("repo") repo: String?): Observable<List<SubscriberResponse>>
}