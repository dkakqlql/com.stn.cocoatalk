package com.stn.feature_chat.room

import com.stn.feature_auth.data.UserDataSource
import com.stn.feature_chat.data.MessageDataSource
import com.stn.feature_chat.data.entity.Message
import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val messageDataSource: MessageDataSource,
    private val userDataSource: UserDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()

    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        if(members.containsKey(username)) {
            throw MemberAlreadyExistsException()
        }
        members[username] = Member(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    suspend fun sendMessage(senderUsername: String, message: String) {
        members.values.forEach { member ->
            val messageEntity = userDataSource.findUserByEmail(senderUsername)?.let { it.id }?.let {
                Message(
                    text = message,
                    user_id = it,
                    timestamp = System.currentTimeMillis(),
                    chat_id = ""
                )
            }
            if (messageEntity != null) {
                messageDataSource.insertMessage(messageEntity)
            }

            val parsedMessage = Json.encodeToString(messageEntity)
            member.socket.send(Frame.Text(parsedMessage))
        }
    }

    suspend fun getAllMessages(username: String): List<Message> {
        return messageDataSource.getAllMessages(username)
    }

    suspend fun tryDisconnect(username: String) {
        members[username]?.socket?.close()
        if(members.containsKey(username)) {
            members.remove(username)
        }
    }
}