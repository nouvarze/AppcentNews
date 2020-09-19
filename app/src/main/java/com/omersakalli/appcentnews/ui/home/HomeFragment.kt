package com.omersakalli.appcentnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.databinding.FragmentHomeBinding
import com.omersakalli.appcentnews.ui.ArticleListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), ArticleListAdapter.OnItemListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.ArticlesRecyclerView.layoutManager = LinearLayoutManager(context)

//        homeViewModel.articles.observe(viewLifecycleOwner,{
//            val adapter = ArticleListAdapter(this)
//            adapter.setList(it)
//            binding.ArticlesRecyclerView.adapter = adapter
//
//        })
//        homeViewModel.getArticles(1,"besiktas")

        lifecycleScope.launch {
            homeViewModel.articlesData.collectLatest {
                pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

        return binding.root
    }

    override suspend fun onItemClick(position: Int) {
        //TODO("Not yet implemented")
    }
}