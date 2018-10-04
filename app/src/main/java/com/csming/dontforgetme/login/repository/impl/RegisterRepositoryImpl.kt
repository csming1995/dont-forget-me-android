package com.csming.dontforgetme.login.repository.impl

import com.csming.dontforgetme.common.model.RegisterResultModel
import com.csming.dontforgetme.api.RegisterApi
import com.csming.dontforgetme.login.repository.RegisterRepository
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class RegisterRepositoryImpl @Inject constructor(
        private val registerApi: RegisterApi
) : RegisterRepository {


    override fun register(phoneNum: String, password: String): RegisterResultModel? {
        val params = HashMap<String, String>()
        params["phone"] = phoneNum
        params["user_password"] = password
        val call = registerApi.register(params)
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}