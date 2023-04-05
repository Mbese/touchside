package com.example.touchside.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchside.repo.BookRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import java.io.FileOutputStream
import java.io.InputStream

class BookViewModel(
    private val repository: BookRepository,
    private val application: Application
) : ViewModel() {

    private val _book: MutableLiveData<String> = MutableLiveData()
    val book: MutableLiveData<String>
        get() = _book

    suspend fun downloadBook() {
        val result = repository.downloadBook()
        val responseBody = result.body()
        if (result.isSuccessful && responseBody != null) {
            _book.value = saveFile(responseBody)
        }
    }

    private fun saveFile(body: ResponseBody): String? {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val path = application.getExternalFilesDir(null)?.path
            val fileOutputStream = FileOutputStream(path)
            fileOutputStream.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return path
        } catch (e: Exception) {
            Log.e("Something went wrong", e.toString())
        } finally {
            input?.close()
        }
        return ""
    }

}