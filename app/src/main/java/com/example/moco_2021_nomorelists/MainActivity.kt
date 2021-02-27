package com.example.moco_2021_nomorelists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemReselectedListener{
            var selectedFragment: Fragment = MapFragment()
            when (it.itemId) {
                R.id.map -> selectedFragment = MapFragment()
                R.id.input -> selectedFragment = InputFragment()
                R.id.scan -> selectedFragment = ScanFragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, selectedFragment)
            transaction.commit()
            return@setOnNavigationItemReselectedListener
        }

        bottomNav.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment = MapFragment()
            when (it.itemId) {
                R.id.map -> selectedFragment = MapFragment()
                R.id.input -> selectedFragment = InputFragment()
                R.id.scan -> selectedFragment = ScanFragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, selectedFragment)
            transaction.commit()
            return@setOnNavigationItemSelectedListener true
        }
    }
}