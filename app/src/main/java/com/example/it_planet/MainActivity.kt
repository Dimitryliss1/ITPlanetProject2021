package com.example.it_planet

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    var page = 1
    var urlVKIT : String = "https://vkist.guap.ru/"
/*
    private val appNavi = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorites -> {
                val intent = Intent(this@MainActivity, favourite_fragment::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                val intent = Intent(this@MainActivity, settings_fragment::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }
 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
       // bottomNavigation.setOnNavigationItemSelectedListener(appNavi)
        val homeFragment = HomeFragment()
        val favouriteFragment = FavouriteFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment (homeFragment)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_home->makeCurrentFragment(homeFragment)
                R.id.nav_favorites->makeCurrentFragment(favouriteFragment)
                R.id.nav_settings->makeCurrentFragment(settingsFragment)
            }
            true
        }

        title = "Habesta"
        listView = findViewById<ListView>(R.id.userlist)
        listNews()

        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val link: String = parent.getItemAtPosition(position).toString().substringAfter(' ')
                .substringBefore(' ')
            val intent = Intent(this@MainActivity, ArticleActivity::class.java)
            startActivity(intent.putExtra("link", link))
        })

    }

    private fun makeCurrentFragment (fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace (R.id.fragment_container, fragment)
            commit()
        }


    private fun listNews(){
        Thread(Runnable {
            val arrayAdapter: ArrayAdapter<String>
            val parserVKIT = ParserVKIT()
            var strings: MutableList<String> = parserVKIT.getHtmlFromWeb(urlVKIT)
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
            runOnUiThread {
                listView.adapter = arrayAdapter
            }
        }).start()
    }

    fun clickPrevPage(v: View){
        val arrayAdapter: ArrayAdapter<String>
        val parserVKIT = ParserVKIT()
        val urlBuilder = StringBuilder()
        page--
        if (page == 0){
            page++
            urlBuilder.append(urlVKIT)
        }
        else {
            urlBuilder.append(urlVKIT).append("page/").append(page)
        }
        var strings: MutableList<String> = parserVKIT.getHtmlFromWeb(urlBuilder.toString())
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
        listView.adapter = arrayAdapter
    }

    fun clickNextPage(v: View){
        var arrayAdapter: ArrayAdapter<String>
        val parserVKIT = ParserVKIT()
        val urlBuilder = StringBuilder()
        page++
        try {
            urlBuilder.append(urlVKIT).append("page/").append(page)
            val doc: Document = Jsoup.connect(urlBuilder.toString()).get()
            var strings: MutableList<String> = parserVKIT.getHtmlFromWeb(urlBuilder.toString())
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
           // listView.adapter = arrayAdapter
            listView.setAdapter(arrayAdapter)
        } catch (e: IOException){
            page--
            urlBuilder.clear()
            urlBuilder.append(urlVKIT).append("page/").append(page)
            var strings: MutableList<String> = parserVKIT.getHtmlFromWeb(urlBuilder.toString())
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
            //listView.adapter = arrayAdapter
            listView.setAdapter(arrayAdapter)
        }

    }
}