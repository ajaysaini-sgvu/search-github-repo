package com.asanarebel.android.ui.activity

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.asanarebel.android.AsanaRebelApplication
import com.asanarebel.android.BuildConfig
import com.asanarebel.android.R
import com.asanarebel.android.ui.search.SearchRepositoryActivity
import com.asanarebel.android.ui.search.SearchRepositoryMvpPresenter
import com.asanarebel.android.ui.search.SearchRepositoryMvpView
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = AsanaRebelApplication::class, constants = BuildConfig::class)
class SearchRepositoryActivityTest {

    private lateinit var activity: SearchRepositoryActivity

    @Mock
    private lateinit var mSearchRepositoryMvpPresenter: SearchRepositoryMvpPresenter<SearchRepositoryMvpView>

    private lateinit var progressBar: ProgressBar
    private lateinit var toolbar: Toolbar

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(SearchRepositoryActivity::class.java).create().get()
        mSearchRepositoryMvpPresenter.onAttach(activity)
        findViews()
    }

    private fun findViews() {
        progressBar = activity.findViewById(R.id.progress_bar)
        toolbar = activity.findViewById(R.id.toolbar)
    }

    @Test
    fun toolbarShouldBePresentAndVisible() {
        assertThat(toolbar, `is`(notNullValue()))
        assertThat(toolbar.visibility, `is`(View.VISIBLE))
    }

    @Test
    fun toolbarShouldHaveProperTitle() {
        val toolbarTitle = activity.findViewById<TextView>(R.id.toolbar_title)
        assertThat(toolbarTitle.text.toString(), `is`(activity.getString(R.string.toolbar_search_repository_title)))
    }

    @Test
    fun progressBarShouldBeGone() {
        assertThat(progressBar.visibility, `is`(View.GONE))
    }

}