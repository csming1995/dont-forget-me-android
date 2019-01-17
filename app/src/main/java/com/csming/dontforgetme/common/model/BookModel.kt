package com.csming.dontforgetme.common.model

class BookModel(
        var owner: UserModel? = null,
        var bookName: String? = null,
        var frontCover: String? = null,
        var description: String? = null
)