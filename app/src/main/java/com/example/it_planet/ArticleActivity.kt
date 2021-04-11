package com.example.it_planet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException


class ArticleActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_article)

        val link: String = getIntent().getStringExtra("link").toString()
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.getSettings().setJavaScriptEnabled(true)
        parsers(link)
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

    fun clickBack(v: View){
        val intent = Intent(this@ArticleActivity, MainActivity::class.java)
        startActivity(intent)
    }
}