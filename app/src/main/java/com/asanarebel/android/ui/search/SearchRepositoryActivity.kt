package com.asanarebel.android.ui.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.asanarebel.android.R
import com.asanarebel.android.data.model.search.RepoItem
import com.asanarebel.android.data.model.search.SearchRepositoryResponse
import com.asanarebel.android.ui.base.BaseActivity
import com.asanarebel.android.ui.detail.RepositoryDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class SearchRepositoryActivity : BaseActivity(), SearchRepositoryMvpView {

    lateinit var searchView: SearchView

    @Inject
    lateinit var mSearchRepositoryMvpPresenter: SearchRepositoryMvpPresenter<SearchRepositoryMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // set the title of the toolbar
        toolbar_title.text = getString(R.string.toolbar_search_repository_title)
        toolbar.inflateMenu(R.menu.menu_repository_search)

        mSearchRepositoryMvpPresenter.onAttach(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_repository_search, menu)

        val searchActionMenuItem = menu.findItem(R.id.menu_search)
        searchView = searchActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                mSearchRepositoryMvpPresenter.onSearchRepositoryClick(query)
                searchActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean = false
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = true

    override fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse) {
        val searchRepositoryAdapter = SearchRepositoryAdapter(searchRepositoryResponse.items, object : SearchRepositoryAdapter.OnItemClickListener {
            override fun onClick(repo: RepoItem) {
                val intent = Intent(this@SearchRepositoryActivity, RepositoryDetailActivity::class.java)

                val bundle = Bundle()
                bundle.putParcelable("repoItem", repo)

                intent.putExtra("myBundle", bundle)
                startActivity(intent)
            }
        })
        recycler_view_repos.adapter = searchRepositoryAdapter
    }

    override fun onDestroy() {
        mSearchRepositoryMvpPresenter.onDetach()
        super.onDestroy()
    }

}
