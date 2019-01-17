package com.csming.dontforgetme.main.repository.impl

import com.csming.dontforgetme.api.BookApi
import com.csming.dontforgetme.api.DailyApi
import com.csming.dontforgetme.common.model.BookModel
import com.csming.dontforgetme.common.model.RecordingModel
import com.csming.dontforgetme.main.repository.BookRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class BookRepositoryImpl @Inject constructor(
        private val bookApi: BookApi,
        private val dailyApi: DailyApi
) : BookRepository {


    override fun getBooks(token: String, observer: Observer<List<BookModel>?>) {
        bookApi.getBooks(token)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    override fun getDailies(token: String, observer: Observer<List<RecordingModel>?>) {
        dailyApi.getDailies(token)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}