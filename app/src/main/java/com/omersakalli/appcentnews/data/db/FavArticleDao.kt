package com.omersakalli.appcentnews.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.omersakalli.appcentnews.data.model.Article

@Dao
interface FavArticleDao {

    @Insert
    suspend fun addArticle(article: Article)

    @Delete
    suspend fun removeArticle(article: Article)

    @Query("SELECT * FROM favorite_articles")
    suspend fun getFavoriteArticles():List<Article>
}