package com.csming.dontforgetme.repository

import com.csming.dontforgetme.common.model.BookModel
import com.csming.dontforgetme.common.model.RecordingModel
import io.reactivex.Observer

/**
 * @author Created by csming on 2018/10/4.
 */
interface BookRepository {

    fun getBooks(token: String, observer: Observer<List<BookModel>?>)

    fun getDailies(token: String, observer: Observer<List<RecordingModel>?>)
}