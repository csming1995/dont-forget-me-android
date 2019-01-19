package com.csming.dontforgetme.common.model

import java.util.*

/**
 * @author Created by csming on 2018/11/21.
 */
class RecordingModel(
        val id: String? = null,
        val auther: UserModel? = null,
        val book: BookModel? = null,
        val text: String,
        val images: List<String>? = null,
        val createTime: Date
)