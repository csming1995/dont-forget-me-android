package com.csming.dontforgetme.common.model

data class NetModel<T>(
        val status: Int? = null,
        val data: T? = null
)

const val BASE_STATE = 1000
const val NET_ERROR = BASE_STATE + 1
const val SUCCESS = BASE_STATE + 2