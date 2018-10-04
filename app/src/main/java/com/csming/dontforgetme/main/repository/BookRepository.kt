package com.csming.dontforgetme.main.repository

import com.csming.dontforgetme.common.model.BooksModel
import com.csming.dontforgetme.common.model.RegisterResultModel

/**
 * @author Created by csming on 2018/10/4.
 */
interface BookRepository {

    fun getBooks(token: String): BooksModel?
}