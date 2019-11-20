package com.example.chatapp.data.models

class MessageModel(
    var contentMeg:String,
    var sender:User,
    var createdAt:String,
    var messageType:String
) {

    companion object{
        var VIEW_TYPE_MESSAGE_SENT = "SENT"
        var VIEW_TYPE_MESSAGE_RECEIVED = "RECEIVED"
    }




}