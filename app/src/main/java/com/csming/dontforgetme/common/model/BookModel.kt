package com.csming.dontforgetme.common.model

class BookModel(
        var owner: UserModel? = null,
        var name: String? = null,
        var cover: String? = null,
        var description: String? = null,
        var idPrivate: Int? = null,
        var createTime: String? = null
)