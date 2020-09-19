package com.omersakalli.appcentnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.omersakalli.appcentnews.Paging.ArticlesPagingSource
import com.omersakalli.appcentnews.data.api.NewsService
import com.omersakalli.appcentnews.data.api.RetrofitInstance
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.utils.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    private val _articles = MutableLiveData<List<Article>>()

    val articles: LiveData<List<Article>> = _articles

    val articlesData = Pager(config = PagingConfig(10)){
        ArticlesPagingSource("besiktas")
    }.flow


    fun getArticles(page:Int, searchParameter:String){
        viewModelScope.launch {
//            val a=Pager(
//                config = PagingConfig(10),
//                remoteMediator = ArticlesRemoteMediator()
//            ).liveData
            //_articles.value=RetrofitInstance.newsServiceInstance.getArticlesList(page,searchParameter,Constants.getApiKey()).body()?.articles

        }
    }
}