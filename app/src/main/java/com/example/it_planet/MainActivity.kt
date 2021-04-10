package com.example.it_planet

import android.content.Intent
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Habesta"
        listView = findViewById<ListView>(R.id.userlist)
        listNews()

        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val link: String = parent.getItemAtPosition(position).toString().substringAfter(' ')
                .substringBefore(' ')
            val intent = Intent(this@MainActivity, ArticleActivity::class.java)
            startActivity(intent.putExtra("link", link))
        })

        //BottomNavigationView bottomNav = findViewById<>(R.id.bottom_navigation);
        //bottomNav.setOnNavigationItemSelectedListener(navListener)

        //BottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    /*private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_home -> {
                val fragment = MainActivity()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorites -> {
                val fragment = profile_fragment()//временное значение
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                val fragment = profile_fragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }*/

    private fun listNews(){
        Thread(Runnable {
            val arrayAdapter: ArrayAdapter<String>
            val parserVKIT = ParserVKIT()
            var strings: MutableList<String> = parserVKIT.getHtmlFromWeb()
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
            runOnUiThread {
                listView.adapter = arrayAdapter
            }
        }).start()
    }
}