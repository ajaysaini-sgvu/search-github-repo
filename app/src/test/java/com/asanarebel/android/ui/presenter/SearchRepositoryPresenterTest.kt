package com.asanarebel.android.ui.presenter

import com.asanarebel.android.AsanaRebelApplication
import com.asanarebel.android.BuildConfig
import com.asanarebel.android.data.DataManager
import com.asanarebel.android.data.model.search.SearchRepositoryResponse
import com.asanarebel.android.ui.search.SearchRepositoryMvpPresenter
import com.asanarebel.android.ui.search.SearchRepositoryMvpView
import com.asanarebel.android.ui.search.SearchRepositoryPresenter
import com.asanarebel.android.ui.utils.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = AsanaRebelApplication::class, constants = BuildConfig::class)
class SearchRepositoryPresenterTest {

    private var activity: SearchRepositoryMvpView = mock(SearchRepositoryMvpView::class.java)

    private var dataManager: DataManager = mock(DataManager::class.java)

    private lateinit var mSearchRepositoryMvpPresenter: SearchRepositoryMvpPresenter<SearchRepositoryMvpView>

    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        mSearchRepositoryMvpPresenter = SearchRepositoryPresenter<SearchRepositoryMvpView>(dataManager, testSchedulerProvider)
        mSearchRepositoryMvpPresenter.onAttach(activity)
    }

    @Test
    fun testSearchRepository() {

        val mockedResponse: SearchRepositoryResponse = mock(SearchRepositoryResponse::class.java)

        doReturn(Observable.just(mockedResponse))
                .`when`(dataManager)
                .searchRepo("android")

        mSearchRepositoryMvpPresenter.onSearchRepositoryClick("android")

        testScheduler.triggerActions()

        verify(activity).showLoading()
        verify(activity).onFetchedRepositories(mockedResponse)
    }

    @After
    fun tearDown() {
        mSearchRepositoryMvpPresenter.onDetach()
    }

}