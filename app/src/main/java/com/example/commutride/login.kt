package com.example.commutride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var edtLogin: Button
    private  lateinit var mAuth: FirebaseAuth
    private lateinit var edtSignUp :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        mAuth = FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.editEmail)
        edtLogin = findViewById(R.id.buttonLogin)
        edtPass = findViewById(R.id.editPassword)
        edtSignUp = findViewById(R.id.buttonsignup)





        edtLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            // Perform your login logic here
            // For simplicity, let's just display a Toast message
            if (email.isEmpty() && password.isEmpty()) {
                // You can replace this with your actual login logic
                //val message = "Login successful for email: $email"
                //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

            login(email,password)
        }

        edtSignUp.setOnClickListener {
            val intent = Intent(this, Signup::class.java )
            startActivity(intent)
        }


    }
    private fun login(email :String , password : String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for login in user
                    val intent = Intent(this, MainActivity::class.java )
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the u
                    Toast.makeText(this, "User does not exist or Incorrect password",Toast.LENGTH_SHORT).show()
                }
            }
    }
}