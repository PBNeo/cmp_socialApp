package com.example.designsystem.models

data class UserModel(val id:String, val name:String, val avatarUrl:String? = null, val location:String? = null)
data class ChatMessage(val id:String, val fromId:String, val text:String?, val imageUrl:String? = null, val time:String, val outgoing:Boolean = false)
