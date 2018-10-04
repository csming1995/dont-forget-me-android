package com.csming.dontforgetme.login.repository

import com.csming.dontforgetme.common.model.LoginResultModel

/**
 * @author Created by csming on 2018/10/4.
 */
interface LoginRepository {

    fun login(userId: String, password: String): LoginResultModel?
}