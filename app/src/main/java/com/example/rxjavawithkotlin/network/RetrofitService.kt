package com.example.rxjavawithkotlin.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {
    @GET("volumes")
    fun getBooksListFromApi(@Query("q") query:String): Observable<BookListModel>

}