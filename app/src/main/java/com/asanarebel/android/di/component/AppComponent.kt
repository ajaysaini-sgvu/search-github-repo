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

package com.asanarebel.android.di.component

import com.asanarebel.android.AsanaRebelApplication
import com.asanarebel.android.di.builder.ActivityBuilder
import com.asanarebel.android.di.module.ActivityModule
import com.asanarebel.android.di.module.HelpersModule
import com.asanarebel.android.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = arrayOf(AndroidSupportInjectionModule::class, HelpersModule::class, NetworkModule::class,
        ActivityBuilder::class, ActivityModule::class))
@Singleton
interface AppComponent {

    fun inject(app: AsanaRebelApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: AsanaRebelApplication): Builder

        fun build(): AppComponent
    }
}