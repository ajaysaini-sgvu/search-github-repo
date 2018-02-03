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

package com.asanarebel.android.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.asanarebel.android.AsanaRebelApplication
import com.asanarebel.android.data.AppDataManager
import com.asanarebel.android.data.DataManager
import com.asanarebel.android.data.pref.AppPreferencesHelper
import com.asanarebel.android.data.pref.PreferencesHelper
import com.asanarebel.android.data.remote.ApiHelper
import com.asanarebel.android.data.remote.ApiHelperImpl
import com.asanarebel.android.utils.rx.AppSchedulerProvider
import com.asanarebel.android.utils.rx.SchedulerProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class HelpersModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: AsanaRebelApplication): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    fun provideContext(app: AsanaRebelApplication): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @Singleton
    fun providePreferenceManager(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper = appPreferencesHelper


    @Provides
    @Singleton
    fun provideSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider = appSchedulerProvider

}