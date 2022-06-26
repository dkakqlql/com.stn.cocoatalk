package com.stn.feature_chat.data

import com.stn.feature_chat.data.entity.Message

interface MessageDataSource {

    suspend fun getAllMessages(username: String): List<Message>

    suspend fun insertMessage(message: Message)
}