package com.csming.dontforgetme.common.model

class LoginResultModel(
        error_body: String? = null,
        val token: String = ""
): BaseModel(error_body)