package com.stn.feature_auth.data.entity

import com.stn.feature_chat.data.entity.Chat
import io.ktor.auth.*
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val username: String,
    val email: String,
    val password: String,
    val chatlist: List<Chat>? = emptyList()
): Principal
