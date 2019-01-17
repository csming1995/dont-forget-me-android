package com.csming.dontforgetme.common.model

/**
 * @author Created by csming on 2018/11/21.
 */
class RecordingModel(
        val recordId: Long? = null,
        val auther: UserModel? = null,
        val book: BookModel? = null,
        val description: String,
        val createTime: String
)