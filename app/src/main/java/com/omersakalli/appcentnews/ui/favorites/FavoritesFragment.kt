package com.omersakalli.appcentnews.ui.favorites

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.data.db.FavoritesDatabase
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.data.repository.ArticlesRepository
import com.omersakalli.appcentnews.databinding.FragmentFavoritesBinding
import com.omersakalli.appcentnews.ui.FavArticlesListAdapter
import com.omersakalli.appcentnews.ui.article.ArticleViewModel
import com.omersakalli.appcentnews.ui.article.ArticleViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment(),FavArticlesListAdapter.OnItemListener {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavArticlesListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val dao = FavoritesDatabase.getInstance(requireContext()).favArticleDao()
        val repository = ArticlesRepository(dao)

        val factory = ArticleViewModelFactory(repository)

        articleViewModel =
            ViewModelProvider(this, factory).get(ArticleViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorites,container,false)

        val root = binding.root
//        val textView: TextView = root.findViewById(R.id.text_favorites)

//        articleViewModel.favArticles.observe()

        initRecyclerView()

        return root
    }

    private fun initRecyclerView(){
        binding.favRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavArticlesListAdapter(this)
        binding.favRecyclerView.adapter = adapter
        displayFavsList()
    }

    private fun displayFavsList() {
        articleViewModel.favArticles.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    override suspend fun onItemClick(article: Article) {
        TODO("Not yet implemented")
    }
}