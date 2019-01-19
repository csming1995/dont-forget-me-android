package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.UserModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Created by csming on 2018/10/4.
 */

interface UserApi {

    @GET(USER_PATH)
    fun login(
            @Query("token") token: String
    ): Observable<UserModel>


}

private const val USER_PATH = "$BASE_PATH/account/login"