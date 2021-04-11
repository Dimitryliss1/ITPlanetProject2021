package com.example.it_planet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ListView
import androidx.fragment.app.Fragment
import org.jsoup.select.Elements
import java.io.IOException


class FavouriteFragment : Fragment() {
    lateinit var listView: ListView
    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_fragment, container,false)
    }
}