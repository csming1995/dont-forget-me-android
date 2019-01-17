package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import retrofit2.http.*
import rx.Observable

/**
 * @author Created by csming on 2018/10/4.
 */

interface AccountApi {

    @GET(GET_VCODE_PATH)
    fun getVCode(
            @Query("tel") tel: String,
            @Query("equipment_id") equipementId: String
    ): Observable<String>

    @POST(REGISTER_PATH)
    @FormUrlEncoded
    fun register(
            @Field("tel") tel: String,
            @Field("password") password: String,
            @Field("code") code: String
    ): Observable<String?>

    @POST(LOGIN_PATH)
    @FormUrlEncoded
    fun login(
            @Field("name") userId: String,
            @Field("password") passwd: String
    ): Observable<String>

}

private const val LOGIN_PATH = "$BASE_PATH/account/login"

private const val GET_VCODE_PATH = "$BASE_PATH/account/vcode"
private const val REGISTER_PATH = "$BASE_PATH/account/register"