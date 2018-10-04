package com.csming.dontforgetme.common.model

class RegisterResultModel(
        error_body: String? = null,
        val result: String? = null
) : BaseModel(error_body)

const val RESULT_SUCCESS = "success"
const val RESULT_FAILURE = "failure"