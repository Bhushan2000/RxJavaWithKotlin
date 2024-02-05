package com.example.rxjavawithkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavawithkotlin.network.BookListModel
import com.example.rxjavawithkotlin.network.RetrofitInstance
import com.example.rxjavawithkotlin.network.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {


    var booksList: MutableLiveData<BookListModel> = MutableLiveData()

    fun getBookListObserver(): MutableLiveData<BookListModel> {
        return booksList
    }

    //  make the api call through retrofit using rxjava
    fun makeApiCall(query: String) {
        val retrofitInstance =
            RetrofitInstance
                .getRetrofitInstance()
                .create(RetrofitService::class.java)

        retrofitInstance
            .getBooksListFromApi(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBooksListObserverRx())

    }

    // pass the function to subscribe
    private fun getBooksListObserverRx(): Observer<BookListModel> {
        return object : Observer<BookListModel> {
            override fun onSubscribe(d: Disposable) {
                // start showing progress indicator
            }

            override fun onNext(t: BookListModel) {
                booksList.postValue(t)

            }

            override fun onError(e: Throwable) {
                booksList.postValue(null)
            }

            override fun onComplete() {
                // hide progressbar
            }

        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}