package com.example.commutride

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class As_Passanger : AppCompatActivity() {
    private lateinit var edtNext: Button
    private lateinit var mdabse : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_as_passanger)
        edtNext = findViewById(R.id.passnext)

        edtNext.setOnClickListener {

            val intent = Intent(this@As_Passanger, MainActivity::class.java )
            startActivity(intent)
        }
    }
}