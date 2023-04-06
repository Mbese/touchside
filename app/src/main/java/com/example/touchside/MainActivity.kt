package com.example.touchside

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.touchside.viewmodel.BookViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : BookViewModel
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        viewModel =  ViewModelProvider(this)[BookViewModel::class.java]
        viewModel.init(this.application)

        lifecycleScope.launch {
            viewModel.downloadBook()
        }

        viewModel.book.observe(this){
            textView.text = it
        }
    }
}