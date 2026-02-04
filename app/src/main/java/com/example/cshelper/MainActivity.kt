package com.example.cshelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var isOtpVerified = false
    private lateinit var userNameValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUserName = findViewById<EditText>(R.id.etUserName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etOTP = findViewById<EditText>(R.id.etOTP)

        val btnSendOTP = findViewById<MaterialButton>(R.id.btnSendOTP)
        val btnGoogleLogin = findViewById<MaterialButton>(R.id.btnGoogle)
        val btnNext = findViewById<Button>(R.id.btnSkip)

        etOTP.visibility = View.GONE
        btnNext.isEnabled = false

        // Google login
        btnGoogleLogin.setOnClickListener {
            val name = etUserName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Welcome $name", Toast.LENGTH_SHORT).show()
            }
        }

        // SEND OTP
        btnSendOTP.setOnClickListener {
            val name = etUserName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (name.isEmpty() || phone.length != 10 || !phone.all { it.isDigit() }) {
                Toast.makeText(this, "Please enter valid name and 10-digit phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userNameValue = name
            isOtpVerified = false

            etOTP.visibility = View.VISIBLE
            etOTP.text.clear()

            Toast.makeText(this, "OTP sent to $phone (dummy: 1234)", Toast.LENGTH_SHORT).show()
            checkNextButton(etUserName, etPhone, etOTP, btnNext)
        }

        // OTP verification (dummy 1234)
        etOTP.addTextChangedListener {
            isOtpVerified = etOTP.text.toString().trim() == "1234"
            checkNextButton(etUserName, etPhone, etOTP, btnNext)
        }

        etUserName.addTextChangedListener { checkNextButton(etUserName, etPhone, etOTP, btnNext) }
        etPhone.addTextChangedListener { checkNextButton(etUserName, etPhone, etOTP, btnNext) }

        // Next button click
        btnNext.setOnClickListener {
            val name = etUserName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill name and phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isOtpVerified) {
                Toast.makeText(this, "Please verify OTP first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, StartingActivity::class.java)
            intent.putExtra("USERNAME", userNameValue)
            startActivity(intent)
            finish()
        }
    }

    private fun checkNextButton(etUserName: EditText, etPhone: EditText, etOTP: EditText, btnNext: Button) {
        val isNameFilled = etUserName.text.toString().trim().isNotEmpty()
        val isPhoneFilled = etPhone.text.toString().trim().length == 10
        val isOtpFilled = etOTP.text.toString().trim().length == 4 && isOtpVerified

        btnNext.isEnabled = isNameFilled && isPhoneFilled && isOtpFilled
    }
}

