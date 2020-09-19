package com.omersakalli.appcentnews.data.api

import com.omersakalli.appcentnews.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Todo: Improve this according to the best practises
//Tried to use singleton pattern might not be the best way to do it
object RetrofitInstance {
    private val retrofitInstance :Retrofit by lazy { instantiateRetrofit() }
    val newsServiceInstance:NewsService by lazy { retrofitInstance.create(NewsService::class.java) }

    private fun instantiateRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        return Retrofit.Builder()
            .baseUrl(Constants.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


}