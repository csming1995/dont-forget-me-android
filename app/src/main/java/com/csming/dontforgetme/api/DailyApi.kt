package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.RecordingModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * @author Created by csming on 2018/10/4.
 */

interface DailyApi {

    @GET(RECORDING_PATH)
    fun getDailies(@Query("token") token: String): Observable<List<RecordingModel>?>
}

private const val RECORDING_PATH = "$BASE_PATH/recordings"