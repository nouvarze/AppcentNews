package com.omersakalli.appcentnews.data.db

import androidx.room.TypeConverter
import com.omersakalli.appcentnews.data.model.Source

class Converters {
    @TypeConverter
    fun sourceToString(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun stringToSource(string: String):Source{
        return Source(null,string)
    }
}