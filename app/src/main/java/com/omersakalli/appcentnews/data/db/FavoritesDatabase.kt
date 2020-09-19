package com.omersakalli.appcentnews.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.omersakalli.appcentnews.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FavoritesDatabase: RoomDatabase() {
    abstract fun favArticleDao(): FavArticleDao

    companion object{
        @Volatile
        private var INSTANCE : FavoritesDatabase?=null

        fun getInstance(context: Context):FavoritesDatabase{
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        FavoritesDatabase::class.java,
                        "favorites_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}