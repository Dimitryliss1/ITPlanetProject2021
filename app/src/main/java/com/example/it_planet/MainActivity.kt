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
        title = "KotlinApp"
        listView = findViewById<ListView>(R.id.userlist)
        listNews()

        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val link: String = parent.getItemAtPosition(position).toString().substringAfter(' ')
                .substringBefore(' ')
            val intent = Intent(this@MainActivity, ArticleActivity::class.java)
            startActivity(intent.putExtra("link", link))
        })

        val profileFragment = profile_fragment()



    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_favorites, fragment)
            commit()
        }

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