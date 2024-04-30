package com.example.commutride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import java.net.UnknownServiceException

class Signup : AppCompatActivity() {
    //  private lateinit var radioGroup: RadioGroup
    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var edtSignup: Button
    private lateinit var edtcontact : EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mdabse: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.editEmail)
        edtSignup = findViewById(R.id.signupbutton)
        edtPass = findViewById(R.id.editPassword)
        edtcontact = findViewById(R.id.editTextPhone)
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@charusat\\.edu\\.in\$")
        val passRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,16}\$")
        val phoneRegex =  Regex("^[6-9]\\d{9}")
        edtSignup.setOnClickListener {




            val email =edtEmail.text.toString()
            val password = edtPass.text.toString()
            val contact = edtcontact.text.toString()



            if(emailRegex.matches(email))
            {
                if(passRegex.matches(password))
                {
                    if(phoneRegex.matches(contact))
                    {
                        signup(email,password, contact)

                    }
                    else
                    {
                        Toast.makeText(this@Signup, "Invalid Mobile Number",Toast.LENGTH_SHORT).show()
                        edtcontact.text.clear()
                    }
                }
                else{
                    Toast.makeText(this@Signup, "Password Should be atleas 8 charater and it containts alphanueric and special Character",Toast.LENGTH_SHORT).show()
                    edtPass.text.clear()
                }
            }
            else{



                Toast.makeText(this@Signup, "Invalid Email Id",Toast.LENGTH_SHORT).show()
                edtEmail.text.clear()
                edtPass.text.clear()
                edtcontact.text.clear()
                val intent = intent
                finish()
                startActivity(intent)
            }

        }

    }

    private fun signup(email :String , password : String, contact: String)
    {

        mAuth.createUserWithEmailAndPassword(email ,password)
            .addOnCompleteListener(this){
                    task ->
                if(task.isSuccessful)
                {
                    writeNewUser(email,mAuth.currentUser?.uid!!,contact)
                    Toast.makeText(this@Signup, "Sign Up sucessfully",Toast.LENGTH_SHORT).show()


                    val intent = Intent(this, City::class.java)
                    finish()
                    startActivity(intent)


                }
                else
                {
                    Toast.makeText(this@Signup, "Invaild email",Toast.LENGTH_SHORT).show()
                }

            }

    }

    private fun writeNewUser(email: String, uid: String, contact: String )
    {
        mdabse = FirebaseDatabase.getInstance().getReference()
        mdabse.child("User").child(uid).setValue(User(email,contact, uid))

    }



}