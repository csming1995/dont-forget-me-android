package com.csming.dontforgetme.main.viewmodel

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

class MainViewModel @Inject constructor(
        private val bookRepository: BookRepository
) : ViewModel() {

    private var token: String = ""

    val isLoading = MutableLiveData<Boolean>()

    private val _bookLiveData = MutableLiveData<ApiResultModel<BooksModel?>>()
    val bookLiveData: LiveData<ApiResultModel<BooksModel?>>
        get() = _bookLiveData

    fun getBooks() {

        rx.Observable.create<BooksModel> {
            isLoading.postValue(true)
            var result: BooksModel? = null
            try {
                result = bookRepository.getBooks(token)
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

            it.onNext(result)
            it.onCompleted()
        }.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BooksModel> {
                    override fun onCompleted() {
                        isLoading.postValue(false)
                    }
                    override fun onError(e: Throwable) {
                        isLoading.postValue(false)
                        Timber.e(e)
                        _bookLiveData.value = ApiResultModel(
                                state = NET_ERROR
                        )
                    }

                    override fun onNext(booksModel: BooksModel) {
                        _bookLiveData.value = ApiResultModel(
                                data = booksModel
                        )
                    }
                })
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun getToken(): String{
        return token
    }
}