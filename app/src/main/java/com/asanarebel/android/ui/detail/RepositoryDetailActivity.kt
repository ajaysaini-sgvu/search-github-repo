package com.asanarebel.android.ui.detail

import android.os.Bundle
import android.view.MenuItem
import com.asanarebel.android.R
import com.asanarebel.android.data.model.search.RepoItem
import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import com.asanarebel.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_repository_detail.*
import javax.inject.Inject

class RepositoryDetailActivity : BaseActivity(), RepositoryDetailMvpView {

    @Inject
    lateinit var mRepositoryDetailMvpPresenter: RepositoryDetailMvpPresenter<RepositoryDetailMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val repo = intent.getBundleExtra("myBundle").getParcelable<RepoItem>("repoItem") as RepoItem

        // set the title of the toolbar
        toolbar_title.text = repo.name

        mRepositoryDetailMvpPresenter.onAttach(this)

        mRepositoryDetailMvpPresenter.onSubscriberRequest(repo.owner?.login, repo.name)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onFetchedSubscribers(subscriberResponse: List<SubscriberResponse>) {
        recycler_view_subscribers.adapter = RepositoryDetailAdapter(subscriberResponse)
    }

}
