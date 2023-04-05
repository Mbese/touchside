package com.example.touchside.di

import com.example.touchside.constants.Constants.Companion.BASE_URL
import com.example.touchside.repo.BookRepository
import com.example.touchside.service.ApiClient
import com.example.touchside.service.BookService
import com.example.touchside.viewmodel.BookViewModel
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        BookViewModel(get(), get())
    }
}

val repoModule = module {
    single {
        BookRepository(get())
    }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    single { provideApi(get()) }
}

val retrofitModule = module {
    fun retrofit(baseUrl: String) = Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun okHttp() = OkHttpClient.Builder()
        .build()

    single {
        okHttp()
    }
    single {
        retrofit(BASE_URL)
    }
    single {
        get<Retrofit>().create(BookService::class.java)
    }
}