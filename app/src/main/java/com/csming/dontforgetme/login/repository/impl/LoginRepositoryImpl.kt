package com.csming.dontforgetme.login.repository.impl

import com.csming.dontforgetme.api.AccountApi
import com.csming.dontforgetme.login.repository.LoginRepository
import rx.Observer
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class LoginRepositoryImpl @Inject constructor(
        private val accountApi: AccountApi
) : LoginRepository {


    override fun login(name: String, password: String, observer: Observer<String?>) {
//        accountApi.login(name, password)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer)

        observer.onNext("token")
    }
}