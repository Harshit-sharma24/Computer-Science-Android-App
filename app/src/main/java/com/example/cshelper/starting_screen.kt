package com.example.cshelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting_screen)

        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        val btnNext = findViewById<Button>(R.id.btnNext)

        val userName = intent.getStringExtra("USERNAME")
        tvUserName.text = userName ?: "User"

        btnNext.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
