package com.example.touchside.repo

import com.example.touchside.service.ApiClient
import com.example.touchside.service.BookService
import okhttp3.ResponseBody
import retrofit2.Response

class BookRepository {
    private val bookService: BookService = ApiClient.getClient()
    suspend fun downloadBook(): Response<ResponseBody> {
        return bookService.getBook()
    }
}