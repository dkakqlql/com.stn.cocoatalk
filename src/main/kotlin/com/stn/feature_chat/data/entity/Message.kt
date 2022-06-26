package com.stn.feature_chat.data.entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    @BsonId
    val id: String = ObjectId().toString(),
    val text: String,
    val timestamp: Long,
    val chat_id: String,
    val user_id: String
)
