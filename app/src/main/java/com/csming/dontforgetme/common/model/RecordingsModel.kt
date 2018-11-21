package com.csming.dontforgetme.common.model

/**
 * @author Created by csming on 2018/11/21.
 */
class RecordingsModel(
        error_body: String? = null,
        val recordings: Array<RecordingModel>? = null
) : BaseModel(error_body)