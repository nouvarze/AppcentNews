package com.omersakalli.appcentnews.Paging

import androidx.paging.PagingSource
import com.omersakalli.appcentnews.data.api.NewsService
import com.omersakalli.appcentnews.data.api.RetrofitInstance
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.utils.Constants
import java.lang.Exception

class ArticlesPagingSource(
    private val service: NewsService,
    private val query:String
)
    : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val page = params.key ?: 1
            val prevKey = if (page > 1) page - 1 else null
            val response:List<Article> = service.getArticlesList(page,query,Constants.getApiKey()).body()!!.articles
            val nextKey = if (response.isNotEmpty()) page + 1 else null
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