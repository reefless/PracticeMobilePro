package com.egco428.practice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.egco428.practice.models.MessageAdapter
import com.egco428.practice.models.userInfo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_mainpage.*

class mainpage : AppCompatActivity() {
    lateinit var dataReference: DatabaseReference
    var msgList = ArrayList<userInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
        val passUser = intent.getStringExtra("username")
        val passPassword = intent.getStringExtra("password")

        dataReference = FirebaseDatabase.getInstance().getReference("UserInfo")
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("Position:", "$i")
            val intent = Intent(this, MapsActivity::class.java )
            intent.putExtra("latitude", msgList.get(i).latitude)
            intent.putExtra("longtitude", msgList.get(i).longtitude)
            startActivity(intent)
        }
        dataReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()) {
                    msgList.clear()
                    for (i in p0.children) {
                        val message = i.getValue(userInfo::class.java)
                        msgList.add(message!!)
                    }
                    val count = msgList.size
                    var x = 0
                    while(x<count){
                        if(msgList.get(x).username == passUser && msgList.get(x).password == passPassword){
                            break
                        }
                        if(x == count -1){
                            finish()
                        }
                        x++
                    }
                    val adapter = MessageAdapter(applicationContext, R.layout.listview, msgList)
                    listView.adapter = adapter
                }

            }
        })
    }
}
