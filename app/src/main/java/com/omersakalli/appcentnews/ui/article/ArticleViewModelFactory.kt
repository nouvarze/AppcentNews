package com.omersakalli.appcentnews.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omersakalli.appcentnews.data.repository.ArticlesRepository

class ArticleViewModelFactory(private val repository: ArticlesRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArticleViewModel::class.java)){
            return ArticleViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown viewmodel class")
    }

}