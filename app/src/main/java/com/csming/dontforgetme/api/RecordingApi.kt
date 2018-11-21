package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.RecordingsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Created by csming on 2018/10/4.
 */

interface RecordingApi {

    @GET(RECORDING_PATH)
    fun getDailies(@Query("token") token: String): Call<RecordingsModel>
}

private const val RECORDING_PATH = "$BASE_PATH/recordings"