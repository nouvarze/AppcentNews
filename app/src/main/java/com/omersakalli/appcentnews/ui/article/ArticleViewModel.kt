package com.omersakalli.appcentnews.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omersakalli.appcentnews.data.db.FavoritesDatabase
import com.omersakalli.appcentnews.data.model.Article
import com.omersakalli.appcentnews.data.repository.ArticlesRepository
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticlesRepository) : ViewModel() {

    val favArticles = repository.articles

    fun toggleFavorite(article: Article) = viewModelScope.launch {


    }

    fun addArticle(article:Article) = viewModelScope.launch {
        repository.add(article)
    }

    fun removeArticle(article: Article) = viewModelScope.launch {
        repository.remove(article)
    }

    fun hasItem(article: Article) = repository.hasItem(article)
}