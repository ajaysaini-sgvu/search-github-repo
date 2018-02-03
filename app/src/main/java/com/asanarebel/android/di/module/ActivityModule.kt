package com.asanarebel.android.di.module

import com.asanarebel.android.ui.detail.RepositoryDetailMvpPresenter
import com.asanarebel.android.ui.detail.RepositoryDetailMvpView
import com.asanarebel.android.ui.detail.RepositoryDetailPresenter
import com.asanarebel.android.ui.search.SearchRepositoryMvpPresenter
import com.asanarebel.android.ui.search.SearchRepositoryMvpView
import com.asanarebel.android.ui.search.SearchRepositoryPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideSearchRepositoryPresenter(presenter: SearchRepositoryPresenter<SearchRepositoryMvpView>): SearchRepositoryMvpPresenter<SearchRepositoryMvpView> = presenter

    @Provides
    fun provideRepositoryDetailPresenter(presenter: RepositoryDetailPresenter<RepositoryDetailMvpView>): RepositoryDetailMvpPresenter<RepositoryDetailMvpView> = presenter

}