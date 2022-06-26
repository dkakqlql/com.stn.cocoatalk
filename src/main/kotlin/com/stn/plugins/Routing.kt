package com.stn.plugins

import com.stn.feature_auth.controller.AuthController
import com.stn.feature_auth.routes.CreateUser
import com.stn.feature_auth.routes.VerifyPassword
import com.stn.feature_auth.routes.VerifyToken
import com.stn.feature_chat.room.RoomController
import com.stn.feature_chat.routes.chatSocket
import com.stn.feature_chat.routes.getAllMessages
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val authController by inject<AuthController>()
    val roomController by inject<RoomController>()

    install(Routing) {
        chatSocket(roomController)
        getAllMessages(roomController)
        VerifyPassword(authController)
        VerifyToken(authController)
        CreateUser(authController)
    }
}
