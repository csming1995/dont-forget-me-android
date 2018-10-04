package com.csming.dontforgetme.common.model

class BooksModel(
        error_body: String? = null,
        val books: Array<BookModel>? = null
) : BaseModel(error_body)