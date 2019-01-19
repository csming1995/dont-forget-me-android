package com.csming.dontforgetme.repository

import com.csming.dontforgetme.common.model.UserModel
import io.reactivex.Observer

/**
 * @author Created by csming on 2018/10/4.
 */
interface UserRepository {

    fun getMe(token: String, observer: Observer<UserModel?>)
}