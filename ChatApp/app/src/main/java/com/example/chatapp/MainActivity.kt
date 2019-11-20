package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.ui.MessageListActivity

class MainActivity : AppCompatActivity() {


    lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWidget()

        btn.setOnClickListener {
            var intent = Intent(this, MessageListActivity::class.java)
            startActivity(intent)
        }

    }

    fun initWidget(){
        btn = findViewById(R.id.nextId)

    }





}
