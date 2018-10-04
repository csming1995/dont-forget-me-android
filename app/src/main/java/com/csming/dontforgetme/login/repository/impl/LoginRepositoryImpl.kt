package com.csming.dontforgetme.login.repository.impl

import com.csming.dontforgetme.common.model.LoginResultModel
import com.csming.dontforgetme.api.LoginApi
import com.csming.dontforgetme.login.repository.LoginRepository
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class LoginRepositoryImpl @Inject constructor(
        private val loginApi: LoginApi
) : LoginRepository {


    override fun login(userId: String, password: String): LoginResultModel? {
        val call = loginApi.login(userId, password)
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}