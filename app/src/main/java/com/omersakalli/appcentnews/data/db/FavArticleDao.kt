package com.omersakalli.appcentnews.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omersakalli.appcentnews.data.model.Article

@Dao
interface FavArticleDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)

    @Delete
    suspend fun removeArticle(article: Article)

    @Query("SELECT * FROM favorite_articles")
    fun getFavoriteArticles():LiveData<List<Article>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_articles WHERE url = :url)")
    fun hasItem(url:String):Boolean

}