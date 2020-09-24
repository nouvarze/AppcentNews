package com.omersakalli.appcentnews.data.repository

import com.omersakalli.appcentnews.data.api.NewsService
import com.omersakalli.appcentnews.data.model.Article
import androidx.paging.PagingData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.omersakalli.appcentnews.Paging.ArticlesPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val service:NewsService) {
    fun getSearchResultStream(query:String): Flow<PagingData<Article>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {ArticlesPagingSource(service,query)}
        ).flow
    }
}