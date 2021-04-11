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

        val homeFragment = ListFragment()
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
    }

    private fun makeCurrentFragment (fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace (R.id.fragment_container, fragment)
            commit()
        }
}