package com.example.testmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {


    var myInputer:EditText? = null
    var myBtn:Button? = null
    var myList:ListView? = null

    var rootList:ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidget()



    }


    fun initWidget(){
        myInputer = findViewById(R.id.myinputId)
        myBtn = findViewById(R.id.addBtnId)
        myList = findViewById(R.id.myListId)

        rootList = ArrayList()
       
    }

    fun addItem(){
        myBtn?.setOnClickListener {

        }
    }
}
