package com.csming.dontforgetme.main.repository.impl

import com.csming.dontforgetme.common.model.BooksModel
import com.csming.dontforgetme.api.BookApi
import com.csming.dontforgetme.main.repository.BookRepository
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class BookRepositoryImpl @Inject constructor(
        private val bookApi: BookApi
) : BookRepository {


    override fun getBooks(token: String): BooksModel? {
        val params = HashMap<String, String>()

        val call = bookApi.getBooks(token)
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}