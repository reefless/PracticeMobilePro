package com.egco428.practice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.egco428.practice.models.userInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class signup : AppCompatActivity() {
    lateinit var dataReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        randomBtn.setOnClickListener {
            val random = Random()
            latitudeSignup.setText("${-90 + (90 - -90) * random.nextDouble()}")
            longtitudeSignup.setText("${-180 + (180 - -180) * random.nextDouble()}")
        }
        addUserBtn.setOnClickListener {
            val user = usernameSignup.text.toString()
            val pass = passwordSignup.text.toString()
            val lat = latitudeSignup.text.toString().toDouble()
            val long = longtitudeSignup.text.toString().toDouble()
            if(user.isEmpty() || pass.isEmpty() || lat.isNaN() || long.isNaN()){
                Toast.makeText(this, "Please input all the required fields", Toast.LENGTH_SHORT).show()
            }else{
                val userInfo = userInfo(user, pass, lat, long)
                dataReference = FirebaseDatabase.getInstance().getReference("UserInfo")
                val messageId = dataReference.push().key
                dataReference.child(messageId).setValue(userInfo).addOnCompleteListener{
                    Toast.makeText(this,"User added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
