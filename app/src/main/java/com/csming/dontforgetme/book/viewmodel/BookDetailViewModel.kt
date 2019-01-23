package com.csming.dontforgetme.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.common.model.NetModel
import com.csming.dontforgetme.common.model.RecordingModel
import com.csming.dontforgetme.common.model.SUCCESS
import com.csming.dontforgetme.repository.BookRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/3.
 */

class BookDetailViewModel @Inject constructor(
        private val bookRepository: BookRepository
) : ViewModel() {

    private var token: String = ""

    private val _dailyLiveData = MutableLiveData<NetModel<List<RecordingModel>?>>()
    val dailyLiveData: LiveData<NetModel<List<RecordingModel>?>>
        get() = _dailyLiveData

    /**
     * 获取日常列表
     */
    fun getDailies() {
        bookRepository.getDailies(token, object : Observer<List<RecordingModel>?> {

            override fun onSubscribe(d: Disposable?) {
//                isLoading.postValue(false)
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
//                isLoading.postValue(false)
                Timber.e(e)
                _dailyLiveData.value = NetModel(
                        status = NET_ERROR
                )
            }

            override fun onNext(recordingmodels: List<RecordingModel>?) {
                _dailyLiveData.value = NetModel(
                        status = SUCCESS,
                        data = recordingmodels
                )
            }
        })
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun getToken(): String {
        return token
    }
}