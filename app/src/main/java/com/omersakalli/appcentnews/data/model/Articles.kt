package com.omersakalli.appcentnews.data.model

data class Articles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)