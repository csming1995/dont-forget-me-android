package com.csming.dontforgetme.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.common.model.NetModel
import com.csming.dontforgetme.common.model.SUCCESS
import com.csming.dontforgetme.login.repository.LoginRepository
import rx.Observer
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/3.
 */
class LoginViewModel @Inject constructor(
        private val loginRepository: LoginRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _loginResultLiveData = MutableLiveData<NetModel<String?>>()
    val loginResultLiveData: LiveData<NetModel<String?>>
        get() = _loginResultLiveData

    fun login(userId: String, passwd: String) {

        loginRepository.login(userId, passwd, object : Observer<String?> {
            override fun onCompleted() {
                isLoading.postValue(false)
            }

            override fun onError(e: Throwable) {
                isLoading.postValue(false)
                Timber.e(e)
                _loginResultLiveData.value = NetModel(
                        status = NET_ERROR
                )
            }

            override fun onNext(message: String?) {
                _loginResultLiveData.value = NetModel(
                        status = SUCCESS,
                        data = message
                )
            }
        })
    }
}