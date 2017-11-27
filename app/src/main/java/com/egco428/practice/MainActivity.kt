package com.egco428.practice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInBtn.setOnClickListener {
            val intent = Intent(this, mainpage::class.java)
            val usrname = usernameLogin.text.toString()
            val pass = passwordLogin.text.toString()
            if(usrname.isEmpty()){
                usernameLogin.error = "Please input your username"
            }else{
                intent.putExtra("username", usrname)
            }
            if(pass.isEmpty()){
                passwordLogin.error = "Please input your password"
            }else{
                intent.putExtra("password", pass)
            }
            if(usrname.isNotEmpty() && pass.isNotEmpty()) {
                startActivity(intent)
            }
        }
        signupBtn.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            usernameLogin.setText("")
            passwordLogin.setText("")
        }
    }
}
