package com.csming.dontforgetme.login.repository

import com.csming.dontforgetme.common.model.RegisterResultModel

/**
 * @author Created by csming on 2018/10/4.
 */
interface RegisterRepository {

    fun register(phoneNum: String, password: String): RegisterResultModel?
}