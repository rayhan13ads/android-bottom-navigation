package com.example.chatapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.data.adapters.MessageListAdapter
import com.example.chatapp.data.models.MessageModel
import com.example.chatapp.data.models.User
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageListActivity : AppCompatActivity() {

    lateinit var megContent: TextView
    lateinit var sentBtn: Button
    var recyclerView: RecyclerView? = null
    var messageListAdapter:MessageListAdapter? = null
    val db = FirebaseFirestore.getInstance()


    var Tag = "MessageListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)
        initWidget()

        getChatList()

    }

    fun initWidget(){
        var recyclerLayout = LinearLayoutManager(this)
        recyclerLayout.reverseLayout = true
       // recyclerLayout.stackFromEnd = true
        megContent = findViewById(R.id.edittext_chatbox)
        sentBtn = findViewById(R.id.button_chatbox_send)
        recyclerView = findViewById(R.id.root_chat_recyclerId)
        messageListAdapter = MessageListAdapter(this)
        recyclerView?.layoutManager = recyclerLayout
        recyclerView?.adapter = messageListAdapter


        sendMessage()
    }


    fun sendMessage(){
        sentBtn.setOnClickListener{

            var user = User()
            user.nickname = "owner"
            user.profile ="https://images.unsplash.com/photo-1567984935975-6c5f5e8e2ac1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var meg = MessageModel(contentMeg = megContent.text.toString(),createdAt = currentDate,messageType = MessageModel.VIEW_TYPE_MESSAGE_SENT,sender = user)
            val message = hashMapOf<String,String>(
                "content" to meg.contentMeg,
                "time" to meg.createdAt,
                "msgType" to meg.messageType,
                "name" to meg.sender.nickname.toString(),
                "profile" to meg.sender.profile.toString()
            )


            db.collection("messages")
                .add(message)
                .addOnSuccessListener { documentReference ->
                    Log.d(Tag, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(Tag, "Error adding document", e)
                }
        }
    }


    fun getChatList(){

        db.collection("messages")
            .get()
            .addOnSuccessListener { result ->
                var chatList = ArrayList<MessageModel>()
                for (document in result) {
                    Log.d(Tag, "${document.id} => ${document.data}")
                    Log.d(Tag, "${document.id} => ${document.data["content"]}")

                    var user = User()
                       user.nickname = document.data["name"] as String
                    Log.d(Tag, "------------------${user.nickname}")
                        user.profile = document.data["profile"] as String

                    var meg = MessageModel(
                        contentMeg = document.data["content"] as String,
                        sender = user,
                        messageType = document.data["msgType"] as String,
                        createdAt = document.data["time"] as String)

                    chatList?.add(meg)
                }

                if (chatList != null){
                    messageListAdapter?.getMessageList(chatList!!)
                }else{
                    Log.i(Tag, "list empty")
                }

            }
            .addOnFailureListener { exception ->
                Log.w(Tag, "Error getting documents.", exception)
            }
    }
}
