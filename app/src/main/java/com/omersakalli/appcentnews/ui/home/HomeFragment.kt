package com.omersakalli.appcentnews.ui.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.databinding.FragmentHomeBinding
import com.omersakalli.appcentnews.ui.ArticleListAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), ArticleListAdapter.OnItemListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private var searchJob: Job? = null
    val adapter = ArticleListAdapter(this)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.ArticlesRecyclerView.layoutManager = LinearLayoutManager(context)

        initAdapter()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: ""

        if(query != null){
            search(query)
            initSearch(query)
        }


//        homeViewModel.articles.observe(viewLifecycleOwner,{
//            val adapter = ArticleListAdapter(this)
//            adapter.setList(it)
//            binding.ArticlesRecyclerView.adapter = adapter
//
//        })
//        homeViewModel.getArticles(1,"besiktas")

//        lifecycleScope.launch {
//            homeViewModel.articlesData.collectLatest {
//                pagingData ->
//                adapter.submitData(pagingData)
//            }
//        }



        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, binding.searchArticle.text.trim().toString())
    }

    override suspend fun onItemClick(position: Int) {
        //TODO("Not yet implemented")
    }

    private fun search(query:String){
        searchJob?.cancel()
        searchJob= lifecycleScope.launch {
            homeViewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun updateArticleListFromInput(){
        binding.searchArticle.text.trim().let {
            if(it.isNotEmpty()){
                search(it.toString())
            }
        }
    }
    private fun initAdapter(){
        binding.ArticlesRecyclerView.adapter = adapter
    }

    private fun initSearch(query: String){
        binding.searchArticle.setText(query)

        binding.searchArticle.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateArticleListFromInput()
                true
            } else {
                false
            }
        }

        binding.searchArticle.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateArticleListFromInput()
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.ArticlesRecyclerView.scrollToPosition(0) }
        }
    }

    private fun showEmptyList(show:Boolean){
        //Todo: figure out this part
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Android"
    }
}