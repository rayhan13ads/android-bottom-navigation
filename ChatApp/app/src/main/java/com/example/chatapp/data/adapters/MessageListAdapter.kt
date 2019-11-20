package com.example.chatapp.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.data.models.MessageModel

class MessageListAdapter(
    var context:Context


):RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {

    private var chat:List<MessageModel>? = null

    fun getMessageList(messageModels:List<MessageModel>){
        this.chat = messageModels
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var inflater = LayoutInflater.from(context)
        var layout = inflater.inflate(R.layout.item_message,parent,false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {

        if (this.chat != null){
            return this.chat!!.size
        }else{
            return  0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var chatItem:MessageModel = this.chat!!.get(position)

            if (MessageModel.VIEW_TYPE_MESSAGE_SENT.equals(chatItem.messageType)){
                holder.sentLayout?.visibility = LinearLayout.VISIBLE
                holder.sentMessageText?.text = chatItem.contentMeg
                holder.sentTimeText?.text = chatItem.createdAt
                holder.receiverLayout?.visibility = LinearLayout.GONE

            }else if (MessageModel.VIEW_TYPE_MESSAGE_RECEIVED.equals(chatItem.messageType)){
                holder.receiverLayout?.visibility = LinearLayout.VISIBLE
                holder.messageText?.text = chatItem.contentMeg
                holder.nameText?.text = chatItem.sender.nickname
                holder.timeText?.text = chatItem.createdAt
                holder.sentLayout?.visibility = LinearLayout.GONE
                Glide
                    .with(context)
                    .load(chatItem.sender.profile)
                    .centerCrop()
                    .into(holder.profilePicture!!)
            }

    }


    inner class ViewHolder(var item: View):RecyclerView.ViewHolder(item){

        // layout

        var sentLayout:LinearLayout? = null
        var receiverLayout:LinearLayout? = null

        // receiver
        var messageText:TextView? = null
        var nameText:TextView? = null
        var timeText:TextView? = null
        var profilePicture:ImageView? = null

        //sent
        var sentMessageText:TextView? = null
        var sentTimeText:TextView? = null

        init {

            sentLayout = item.findViewById(R.id.sentLayoutId) as LinearLayout
            receiverLayout = item.findViewById(R.id.receiverLayoutId) as LinearLayout

            messageText =item.findViewById(R.id.text_message_body)
            nameText = item.findViewById(R.id.text_message_name)
            timeText = item.findViewById(R.id.text_message_time)
            profilePicture = item.findViewById(R.id.image_message_profile)

            sentMessageText = item.findViewById(R.id.sent_text_message_body)
            sentTimeText = item.findViewById(R.id.sent_text_message_time)
        }




    }
}