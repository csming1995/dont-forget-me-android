package com.csming.dontforgetme.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.ApiResultModel
import com.csming.dontforgetme.common.model.BooksModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.main.repository.BookRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/3.
 */

class AddBookViewModel @Inject constructor(
) : ViewModel()