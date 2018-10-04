package com.csming.dontforgetme.common.model

class BookModel(
        error_body: String? = null,
        var owner: UserModel? = null,
        var bookName: String? = null,
        var frontCover: String? = null,
        var description: String? = null
) : BaseModel(error_body)