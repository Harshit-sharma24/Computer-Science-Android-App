package com.example.cshelper // <- apna actual package name yahan dalna

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var drawerLogin: TextView
    private lateinit var drawerLeaderboard: TextView
    private lateinit var drawerBookmarks: TextView
    private lateinit var drawerSettings: TextView

    private lateinit var card1: CardView
    private lateinit var card2: CardView
    private lateinit var card3: CardView
    private lateinit var card4: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // ================= TOOLBAR =================
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        // ================= DRAWER =================
        drawerLayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // ================= DRAWER TEXTVIEWS =================
        drawerLogin = findViewById(R.id.drawerLogin)
        drawerLeaderboard = findViewById(R.id.drawerLeaderboard)
        drawerBookmarks = findViewById(R.id.drawerBookmarks)
        drawerSettings = findViewById(R.id.drawerSettings)

        drawerLogin.setOnClickListener { Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show() }
        drawerLeaderboard.setOnClickListener { Toast.makeText(this, "Leaderboard clicked", Toast.LENGTH_SHORT).show() }
        drawerBookmarks.setOnClickListener { Toast.makeText(this, "Bookmarks clicked", Toast.LENGTH_SHORT).show() }
        drawerSettings.setOnClickListener { Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show() }

        // ================= BOTTOM NAVIGATION =================
        bottomNavigation = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> { Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show(); true }
                R.id.search -> { Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show(); true }
                R.id.profile -> { Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show(); true }
                R.id.courses-> { Toast.makeText(this, "courses clicked", Toast.LENGTH_SHORT).show(); true }
                R.id.menu-> { Toast.makeText(this, "menu clicked", Toast.LENGTH_SHORT).show(); true }
                else -> false
            }
        }

        // ================= CARDVIEWS =================
        card1 = findViewById(R.id.card1)
        card2 = findViewById(R.id.card2)
        card3 = findViewById(R.id.card3)
        card4 = findViewById(R.id.card4)

        card1.setOnClickListener { Toast.makeText(this, "Card 1 clicked", Toast.LENGTH_SHORT).show() }
        card2.setOnClickListener { Toast.makeText(this, "Card 2 clicked", Toast.LENGTH_SHORT).show() }
        card3.setOnClickListener { Toast.makeText(this, "Card 3 clicked", Toast.LENGTH_SHORT).show() }
        card4.setOnClickListener { Toast.makeText(this, "Card 4 clicked", Toast.LENGTH_SHORT).show() }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

