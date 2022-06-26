package com.stn.plugins

import com.stn.feature_chat.session.ChatSession
import io.ktor.sessions.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.util.*

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<ChatSession>("SESSION")
    }

    // Change to sendToMe fun, maybe?
    intercept(ApplicationCallPipeline.Features) {

        if(call.sessions.get<ChatSession>() == null) {
            val username = call.parameters["username"] ?: "게스트"
            call.sessions.set(ChatSession(username, generateNonce()))
        }
    }
}
