package com.example.it_planet

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException


class ArticleActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val link: String = getIntent().getStringExtra("link").toString()

        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.getSettings().setJavaScriptEnabled(true)
        parsers(link)
        val actionbar = supportActionBar
        actionbar!!.title = "View Article"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun parsers(url :String){
        Thread(Runnable {
            val stringBuilder = StringBuilder()
            val parserVKIT = ParserVKIT()
            stringBuilder.append(parserVKIT.parse(url))
            runOnUiThread {
                webView.loadData(stringBuilder.toString(), "text/html", null)
            }
        }).start()
    }
}