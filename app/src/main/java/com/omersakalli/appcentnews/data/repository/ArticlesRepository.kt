package com.omersakalli.appcentnews.data.repository

import com.omersakalli.appcentnews.data.db.FavArticleDao
import com.omersakalli.appcentnews.data.model.Article

class ArticlesRepository(private val dao:FavArticleDao) {
    val articles = dao.getFavoriteArticles()

    suspend fun add(article: Article){
        dao.addArticle(article)
    }

    suspend fun remove(article: Article){
        dao.removeArticle(article)
    }

    fun hasItem(article: Article):Boolean{
        return dao.hasItem(article.url)
    }
}