package com.csming.dontforgetme.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.common.model.NetModel
import com.csming.dontforgetme.common.model.SUCCESS
import com.csming.dontforgetme.repository.RegisterRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/3.
 */
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _registerResultLiveData = MutableLiveData<NetModel<String?>>()
    val registerResultLiveData: LiveData<NetModel<String?>>
        get() = _registerResultLiveData

    /**
     * 验证手机号是否合法
     */
    fun isPhoneNumLegal(phoneNum: String): Boolean {
        val pattern = Pattern.compile(PHONE_PATTERN)
        val matcher = pattern.matcher(phoneNum)
        return matcher.find()
    }

    fun register(phoneNum: String, password: String) {

        isLoading.postValue(true)
        registerRepository.register("", phoneNum, password, object : Observer<String?> {
            override fun onComplete() {
                isLoading.postValue(false)
            }

            override fun onSubscribe(d: Disposable?) {}

            override fun onError(e: Throwable) {
                isLoading.postValue(false)
                Timber.e(e)
                _registerResultLiveData.value = NetModel(
                        status = NET_ERROR
                )
            }

            override fun onNext(message: String?) {
                _registerResultLiveData.value = NetModel(
                        status = SUCCESS,
                        data = message
                )
            }
        })
    }

}

private const val PHONE_PATTERN = "^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}\$"