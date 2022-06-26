package com.stn.feature_chat.data.entity

import com.stn.feature_auth.data.entity.User
import org.bson.codecs.pojo.annotations.BsonId
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class Chat(
    @BsonId
    val id: String = ObjectId().toString(),
    val participant: List<User>,
    val messages: List<Message>
)
