package com.stn.feature_auth.routes

import com.stn.feature_auth.controller.AlreadyExistUser
import com.stn.feature_auth.controller.AuthController
import com.stn.feature_auth.controller.DismatchedUserCredentialException
import com.stn.feature_auth.controller.NonExistUser
import com.stn.feature_auth.data.entity.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.koin.core.logger.Logger

fun Route.VerifyPassword(authController: AuthController) {
    post("/login") {
        val email = call.receive<User>().email
        try {
            val user = authController.verifyUser(email)
            call.respond(user)
        } catch (e: DismatchedUserCredentialException) {
            call.respond(HttpStatusCode.Conflict)
        } catch (e: NonExistUser) {
            call.respond(HttpStatusCode.Conflict)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

fun Route.VerifyToken(authController: AuthController) {
    authenticate("auth-jwt") {
        get("/token") {
            val principal = call.principal<JWTPrincipal>()
            val expiresAt = principal!!.expiresAt?.time?.compareTo(System.currentTimeMillis())
            call.respondText("Token is expired at $expiresAt ms.")
        }
    }
}

fun Route.CreateUser(authController: AuthController) {
    post("/signup") {
        val user = call.receive<User>()
        try {
            authController.insertUser(user)
            call.respond(HttpStatusCode.OK)
        } catch (e: AlreadyExistUser) {
            call.respond(HttpStatusCode.Conflict)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}