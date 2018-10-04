package com.csming.dontforgetme.common.model

/**
 * @author Created by csming on 2018/10/4.
 */
data class ApiResultModel<T>(
        val state: Int = SUCCESS,
        val data: T? = null
)

const val BASE_STATE = 1000
const val NET_ERROR = BASE_STATE + 1
const val SUCCESS = BASE_STATE + 2