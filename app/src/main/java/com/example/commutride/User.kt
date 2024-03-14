package com.example.commutride

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class User {

    var email : String? =null
    var uid : String? =null
    var contact :String? =null

    constructor(){}

    constructor(email: String? , uid : String?, contact: String?)
    {
        this.email =email
        this.uid =uid
        this.contact =contact
    }
}