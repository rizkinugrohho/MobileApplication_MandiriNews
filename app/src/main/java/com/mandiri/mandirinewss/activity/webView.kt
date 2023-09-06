package com.mandiri.mandirinewss.activity

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mandiri.mandirinewss.databinding.ActivityWebViewBinding


class webView() : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backBtn = binding.backBtn
        val webView = binding.webView

        val url: String = intent.getStringExtra("url")!!
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        backBtn.setOnClickListener(){
            val intentBack = Intent(this, MainActivity::class.java)
            startActivity(intentBack)
        }
    }
}