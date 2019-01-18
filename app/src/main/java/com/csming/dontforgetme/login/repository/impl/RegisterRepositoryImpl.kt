package com.csming.dontforgetme.login.repository.impl

import com.csming.dontforgetme.api.AccountApi
import com.csming.dontforgetme.login.repository.RegisterRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class RegisterRepositoryImpl @Inject constructor(
        private val accountApi: AccountApi
) : RegisterRepository {


    override fun register(tel: String, phoneNum: String, password: String, observer: Observer<String?>) {
        accountApi.register(tel, phoneNum, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}