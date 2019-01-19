package com.csming.dontforgetme.repository.impl

import com.csming.dontforgetme.api.UserApi
import com.csming.dontforgetme.common.model.UserModel
import com.csming.dontforgetme.repository.UserRepository
import io.reactivex.Observer
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class UserRepositoryImpl @Inject constructor(
        private val userApi: UserApi
) : UserRepository {


    override fun getMe(token: String, observer: Observer<UserModel?>) {
//        userApi.login(token)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer)

        val userModel = UserModel(
                "",
                "少棉",
                "",
                ""
        )
        observer.onNext(userModel)
    }
}