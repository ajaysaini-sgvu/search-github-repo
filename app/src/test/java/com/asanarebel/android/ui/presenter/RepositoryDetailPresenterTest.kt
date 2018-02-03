package com.asanarebel.android.ui.presenter

import com.asanarebel.android.AsanaRebelApplication
import com.asanarebel.android.BuildConfig
import com.asanarebel.android.data.DataManager
import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import com.asanarebel.android.ui.detail.RepositoryDetailMvpView
import com.asanarebel.android.ui.detail.RepositoryDetailPresenter
import com.asanarebel.android.ui.utils.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = AsanaRebelApplication::class, constants = BuildConfig::class)
class RepositoryDetailPresenterTest {

    private var activity: RepositoryDetailMvpView = Mockito.mock(RepositoryDetailMvpView::class.java)

    private var dataManager: DataManager = Mockito.mock(DataManager::class.java)

    private lateinit var mRepositoryDetailPresenter: RepositoryDetailPresenter<RepositoryDetailMvpView>

    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        mRepositoryDetailPresenter = RepositoryDetailPresenter<RepositoryDetailMvpView>(dataManager, testSchedulerProvider)
        mRepositoryDetailPresenter.onAttach(activity)
    }

    @Test
    fun testFetchSubscribers() {

        val mockedResponse: SubscriberResponse = Mockito.mock(SubscriberResponse::class.java)

        val subscribersList = ArrayList<SubscriberResponse>()
        subscribersList.add(mockedResponse)

        Mockito.doReturn(Observable.just(subscribersList))
                .`when`(dataManager)
                .fetchSubscribers("crashub", "crash")

        mRepositoryDetailPresenter.onSubscriberRequest("crashub", "crash")

        testScheduler.triggerActions()

        Mockito.verify(activity).showLoading()
        Mockito.verify(activity).onFetchedSubscribers(subscribersList)
    }

    @After
    fun tearDown() {
        mRepositoryDetailPresenter.onDetach()
    }

}