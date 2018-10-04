package com.csming.dontforgetme.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.model.ApiResultModel
import com.csming.dontforgetme.common.model.NET_ERROR
import com.csming.dontforgetme.common.model.RegisterResultModel
import com.csming.dontforgetme.login.repository.RegisterRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/3.
 */
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    private val _registerResultLiveData = MutableLiveData<ApiResultModel<RegisterResultModel?>>()
    val registerResultLiveData: LiveData<ApiResultModel<RegisterResultModel?>>
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

        rx.Observable.create<RegisterResultModel> {
//            isLoading.value = true
            var result: RegisterResultModel? = null
            try {
                result = registerRepository.register(phoneNum, password)
            } catch (e: Exception) {
                it.onError(e)
            }

            it.onNext(result)
            it.onCompleted()
        }.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<RegisterResultModel> {
                    override fun onCompleted() {
//                        isLoading.value = false
                    }
                    override fun onError(e: Throwable) {
//                        isLoading.value = false
                        Timber.e(e)
                        _registerResultLiveData.value = ApiResultModel(
                                state = NET_ERROR
                        )
                    }

                    override fun onNext(registerResultModel: RegisterResultModel?) {
                        _registerResultLiveData.value = ApiResultModel(
                                data = registerResultModel
                        )
                    }
                })
    }

}

private const val PHONE_PATTERN = "^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}\$"