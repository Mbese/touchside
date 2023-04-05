package com.example.touchside.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface BookService {
    @GET("2600/2600-0.txt")
    suspend fun getBook(): Response<ResponseBody>
}