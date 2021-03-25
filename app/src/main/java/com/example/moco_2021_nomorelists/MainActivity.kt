package com.example.moco_2021_nomorelists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moco_2021_nomorelists.Model.User
import com.example.moco_2021_nomorelists.View.InputActivity
import com.example.moco_2021_nomorelists.View.MapFragment
import com.example.moco_2021_nomorelists.View.ScanFragment
import com.example.moco_2021_nomorelists.ViewModel.UserListAdapter
import com.example.moco_2021_nomorelists.ViewModel.UserViewModel
import com.example.moco_2021_nomorelists.ViewModel.UserViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as NMLApplication).repository)
    }

    private val inputActivityRequestCode = 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == inputActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringArrayExtra(InputActivity.EXTRA_REPLY)?.let {
                val user = User(null, it[0], it[1], it[2], it[3].toInt(), it[4].toLong(), it[5])
                userViewModel.insert(user)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Nicht alle Felder wurden richtig befüllt!",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.allUsers.observe(this, Observer { users ->
            users?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivityForResult(intent, inputActivityRequestCode)
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemReselectedListener{
            var selectedFragment: Fragment = MapFragment()
            var users = false
            when (it.itemId) {
                R.id.map -> {selectedFragment = MapFragment()
                    users = false}
                R.id.input -> users = true
                R.id.scan -> {selectedFragment = ScanFragment()
                    users = false}
            }
            val transaction = supportFragmentManager.beginTransaction()
            if (users && supportFragmentManager.findFragmentById(R.id.fragment_container) != null) {
                transaction.remove(supportFragmentManager.findFragmentById(R.id.fragment_container)!!)
                transaction.commit()
            } else if (users) {
                transaction.commit()
            } else {
                transaction.replace(R.id.fragment_container, selectedFragment)
                transaction.commit()
            }
            return@setOnNavigationItemReselectedListener
        }

        bottomNav.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment = MapFragment()
            var users = false
            when (it.itemId) {
                R.id.map -> {selectedFragment = MapFragment()
                    users = false}
                R.id.input -> users = true
                R.id.scan -> {selectedFragment = ScanFragment()
                    users = false}
            }
            val transaction = supportFragmentManager.beginTransaction()
            if (users && supportFragmentManager.findFragmentById(R.id.fragment_container) != null) {
                transaction.remove(supportFragmentManager.findFragmentById(R.id.fragment_container)!!)
                transaction.commit()
            } else if (users) {
                transaction.commit()
            } else {
                transaction.replace(R.id.fragment_container, selectedFragment)
                transaction.commit()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}