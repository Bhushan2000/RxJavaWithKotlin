package com.example.rxjavawithkotlin.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val baseURL = "https://www.googleapis.com/books/v1/"//volumes?q=harry

        fun getRetrofitInstance():Retrofit{
           return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

        }
    }
}