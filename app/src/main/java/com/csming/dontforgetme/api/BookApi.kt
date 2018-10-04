package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.BooksModel
import retrofit2.Call
import retrofit2.http.*

/**
 * @author Created by csming on 2018/10/4.
 */

interface BookApi {

    @GET(BOOKS_PATH)
    fun getBooks(@Query("token") token: String): Call<BooksModel>
}

private const val BOOKS_PATH = "$BASE_PATH/books"