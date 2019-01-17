package com.csming.dontforgetme.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.BookModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.common.model.NetModel
import com.csming.dontforgetme.common.model.RecordingModel
import com.csming.dontforgetme.main.repository.BookRepository
import rx.Observer
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

    private val _bookLiveData = MutableLiveData<NetModel<List<BookModel>?>>()
    val bookLiveData: LiveData<NetModel<List<BookModel>?>>
        get() = _bookLiveData

    private val _dailyLiveData = MutableLiveData<NetModel<List<RecordingModel>?>>()
    val dailyLiveData: LiveData<NetModel<List<RecordingModel>?>>
        get() = _dailyLiveData

    fun getBooks() {

        bookRepository.getBooks(token, object : Observer<List<BookModel>?> {
            override fun onCompleted() {
                isLoading.postValue(false)
            }

            override fun onError(e: Throwable) {
                isLoading.postValue(false)
                Timber.e(e)
                _bookLiveData.value = NetModel(
                        status = NET_ERROR
                )
            }

            override fun onNext(bookModels: List<BookModel>?) {
                _bookLiveData.value = NetModel(
                        data = bookModels
                )
            }
        })
    }

    /**
     * 获取日常列表
     */
    fun getDailies() {
        bookRepository.getDailies(token, object : Observer<List<RecordingModel>?> {
            override fun onCompleted() {
                isLoading.postValue(false)
            }

            override fun onError(e: Throwable) {
                isLoading.postValue(false)
                Timber.e(e)
                _dailyLiveData.value = NetModel(
                        status = NET_ERROR
                )
            }

            override fun onNext(recordingmodels: List<RecordingModel>?) {
                _dailyLiveData.value = NetModel(
                        data = recordingmodels
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