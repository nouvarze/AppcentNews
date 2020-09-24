package com.omersakalli.appcentnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.omersakalli.appcentnews.Paging.ArticlesPagingSource
import com.omersakalli.appcentnews.data.api.NewsService
import com.omersakalli.appcentnews.data.api.RetrofitInstance
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.data.repository.NewsRepository
import com.omersakalli.appcentnews.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

//    private val _articles = MutableLiveData<List<Article>>()

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Article>>? = null

    private val repository: NewsRepository = NewsRepository(RetrofitInstance.newsServiceInstance)

//    private var articleToView: Article?=null

    fun searchRepo(queryString: String):Flow<PagingData<Article>>{
        val lastResult = currentSearchResult
        if(queryString==currentQueryValue && lastResult !=null){
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Article>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult=newResult
        return newResult
    }

//    fun setArticleToView(article: Article){
//        articleToView = article
//    }

//    fun getArticleToView():Article{
//        return articleToView!!
//    }

//    val articles: LiveData<List<Article>> = _articles

//    val articlesData = Pager(
//        config = PagingConfig(10),
//        pagingSourceFactory = { ArticlesPagingSource("besiktas") }).flow


//    fun getArticles(page: Int, searchParameter: String) {
//        viewModelScope.launch {
////            val a=Pager(
////                config = PagingConfig(10),
////                remoteMediator = ArticlesRemoteMediator()
////            ).liveData
//            //_articles.value=RetrofitInstance.newsServiceInstance.getArticlesList(page,searchParameter,Constants.getApiKey()).body()?.articles
//
//        }
//    }
}