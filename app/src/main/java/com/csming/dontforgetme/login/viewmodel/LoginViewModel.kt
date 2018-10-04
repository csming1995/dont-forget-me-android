package com.csming.dontforgetme.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.ApiResultModel
import com.csming.dontforgetme.common.model.LoginResultModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.common.model.RegisterResultModel
import com.csming.dontforgetme.login.repository.LoginRepository
import com.csming.dontforgetme.login.repository.RegisterRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/3.
 */
class LoginViewModel @Inject constructor(
        private val loginRepository: LoginRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _loginResultLiveData = MutableLiveData<ApiResultModel<LoginResultModel?>>()
    val loginResultLiveData: LiveData<ApiResultModel<LoginResultModel?>>
        get() = _loginResultLiveData

    fun login(userId: String, passwd: String) {

        rx.Observable.create<LoginResultModel> {
            isLoading.postValue(true)
            var result: LoginResultModel? = null
            try {
                result = loginRepository.login(userId, passwd)
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

            it.onNext(result)
            it.onCompleted()
        }.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LoginResultModel> {
                    override fun onCompleted() {
                        isLoading.postValue(false)
                    }
                    override fun onError(e: Throwable) {
                        isLoading.postValue(false)
                        Timber.e(e)
                        _loginResultLiveData.value = ApiResultModel(
                                state = NET_ERROR
                        )
                    }

                    override fun onNext(loginResultModel: LoginResultModel?) {
                        _loginResultLiveData.value = ApiResultModel(
                                data = loginResultModel
                        )
                    }
                })
    }
}