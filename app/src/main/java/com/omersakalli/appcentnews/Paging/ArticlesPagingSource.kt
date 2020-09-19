package com.omersakalli.appcentnews.Paging

import androidx.paging.PagingSource
import com.omersakalli.appcentnews.data.api.NewsService
import com.omersakalli.appcentnews.data.api.RetrofitInstance
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.utils.Constants
import java.lang.Exception

class ArticlesPagingSource(
    val searchQuery:String
)
    : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val page = params.key ?: 1
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (response.items.isNotEmpty()) pageNumber + 1 else null
            val response:List<Article> = RetrofitInstance.newsServiceInstance.getArticlesList(page,searchQuery,Constants.getApiKey()).body()!!.articles
            return LoadResult.Page(
                response,
                prevKey,
                nextKey
            )
        }
        catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

}