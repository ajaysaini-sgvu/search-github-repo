package com.asanarebel.android.data.model.search

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(

        @Json(name = "name")
        val name: String?,

        @Json(name = "spdx_id")
        val spdxId: String?,

        @Json(name = "key")
        val key: String?,

        @Json(name = "url")
        val url: String?
) : Parcelable