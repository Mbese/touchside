package com.example.touchside.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchside.repo.BookRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import java.io.FileOutputStream
import java.io.InputStream

class BookViewModel : ViewModel() {
    private lateinit var repository: BookRepository
    private lateinit var application: Application

    fun init(application: Application) {
        repository = BookRepository()
        this.application = application
    }

    private val _book: MutableLiveData<String> = MutableLiveData()
    val book: MutableLiveData<String>
        get() = _book

    suspend fun downloadBook() {
        val result = repository.downloadBook()
        val responseBody = result.body()
        if (result.isSuccessful && responseBody != null) {
            _book.value = responseBody.string()
        }
    }

}