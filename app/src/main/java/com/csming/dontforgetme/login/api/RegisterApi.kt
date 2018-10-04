package com.csming.dontforgetme.login.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.RegisterResultModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Created by csming on 2018/10/4.
 */

interface RegisterApi {

    @POST(REGISTER_PATH)
    fun register(@Body params: Map<String, String>): Call<RegisterResultModel>
}

private const val REGISTER_PATH = "$BASE_PATH/register"