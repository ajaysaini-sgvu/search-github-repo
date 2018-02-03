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

import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackWrapper<T : Any> : DisposableObserver<T>() {

    protected abstract fun onSuccess(t: T)

    protected abstract fun onError(any: Any)

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> {
                val responseBody = (e).response().errorBody()
                responseBody?.let {
                    onError(getErrorMessage(it))
                }
            }
            is SocketTimeoutException -> {
                onError("SocketTimeoutException")
            }
            is IOException -> {
                onError("NetworkException")
            }
            else -> {
                e.message?.let {
                    onError("UnknownException")
                }
            }
        }
    }


    override fun onComplete() {

    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        val jsonObject = JSONObject(responseBody.string())
        return jsonObject.getString(("message"))
    }
}