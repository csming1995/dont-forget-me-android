package com.csming.dontforgetme.api

import com.csming.dontforgetme.common.config.BASE_PATH
import com.csming.dontforgetme.common.model.BookModel
import retrofit2.http.*
import rx.Observable

/**
 * @author Created by csming on 2018/10/4.
 */

interface BookApi {

    @DELETE(BOOK_PATH)
    @FormUrlEncoded
    fun deleteBook(
            @Query("token") token: String
    ): Observable<Any>

    @GET(BOOK_LIST_PATH)
    @FormUrlEncoded
    fun getBooks(
            @Query("token") token: String
    ): Observable<List<BookModel>?>

    @GET(BOOK_PATH)
    fun getBook(
            @Path("id")
            @Query("token") token: String
    ): Observable<BookModel?>

    @POST(BOOK_PATH)
    @FormUrlEncoded
    fun addBook(
            @Field("token") token: String,
            @Field("name") name: String,
            @Field("description") description: String? = null,
            @Field("cover") cover: String? = null,
            @Field("is_private") isPrivate: Int? = null
    ): Observable<Any>

    @POST(BOOK_PATH)
    @FormUrlEncoded
    fun editBook(
            @Path("id") id: String,
            @Field("token") token: String,
            @Field("name") name: String,
            @Field("description") description: String? = null,
            @Field("cover") cover: String? = null,
            @Field("is_private") isPrivate: Int? = null
    ): Observable<Any>
}

private const val BOOK_LIST_PATH = "$BASE_PATH/book/list"
private const val BOOK_PATH = "$BASE_PATH/book"