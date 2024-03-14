package com.example.commutride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Choice : AppCompatActivity() {

    private lateinit var edtPassenger: Button
    private lateinit var edtDriver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)




            edtDriver= findViewById(R.id.driver)
            edtPassenger= findViewById(R.id.passenger)

            edtDriver.setOnClickListener {

                val intent = Intent(this@Choice,As_Driver::class.java)
                startActivity(intent)
            }

            edtPassenger.setOnClickListener {

                val intent = Intent(this@Choice,As_Passanger::class.java)
                startActivity(intent)
            }


    }
}