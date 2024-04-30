package com.example.commutride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class As_Driver : AppCompatActivity() {
    private lateinit var edtNext: Button
    private lateinit var mdabse :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_as_driver)

            edtNext = findViewById(R.id.drivernext)
       

        edtNext.setOnClickListener {

            val intent = Intent(this@As_Driver, MainActivity::class.java )
            finish()
            startActivity(intent)
        }
    }

}