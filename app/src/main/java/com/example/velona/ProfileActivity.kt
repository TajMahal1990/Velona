package com.example.velona

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)
        bottomNav = findViewById(R.id.bottomNavigationView)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        // Клик по иконке меню в тулбаре — открыть drawer
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Обработка кликов в боковом меню
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // TODO: перейти на главную
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_profile -> {
                    // Уже в профиле, просто закрыть
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_settings -> {
                    // TODO: открыть настройки
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }

        // Обработка кликов нижнего меню
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // TODO: перейти на главную
                    true
                }
                R.id.menu_profile -> {
                    // Уже в профиле
                    true
                }
                else -> false
            }
        }
    }
}
