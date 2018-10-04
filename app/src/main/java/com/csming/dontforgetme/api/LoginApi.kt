package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.LoginResultModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author Created by csming on 2018/10/4.
 */

interface LoginApi {

    @POST(LOGIN_PATH)
    @FormUrlEncoded
    fun login(@Field("user_id") userId : String, @Field("pd") passwd: String): Call<LoginResultModel>
}

private const val LOGIN_PATH = "$BASE_PATH/login"