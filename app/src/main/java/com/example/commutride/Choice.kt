package com.example.commutride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Choice : AppCompatActivity() {

    private lateinit var edtPassenger: Button
    private lateinit var edtDriver: Button
    private lateinit var mdabse: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)




            edtDriver= findViewById(R.id.driver)
            edtPassenger= findViewById(R.id.passenger)


            edtDriver.setOnClickListener {

                val role= "driver"
                addToDatabase(role)
                startActivity(intent)

                val intent1 = Intent(this@Choice,As_Driver::class.java)
                startActivity(intent1)
            }

            edtPassenger.setOnClickListener {
                val role= "passenger"
                addToDatabase(role)
                startActivity(intent)
                val intent1 = Intent(this@Choice,As_Passanger::class.java)
                startActivity(intent1)
            }


    }

    private fun addToDatabase(role:String)
    {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        mdabse = FirebaseDatabase.getInstance().getReference()

        mdabse.child("User").child(uid!!).child("Role").setValue(role)

    }
}