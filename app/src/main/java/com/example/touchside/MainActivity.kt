package com.example.touchside

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.touchside.viewmodel.BookViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel by inject<BookViewModel>()
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.downloadBook()
        }

        viewModel.book.observe(this){
            textView.text = it
        }
    }
}