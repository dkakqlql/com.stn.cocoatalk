package com.stn.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.stn.feature_auth.util.JwtSettings
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import java.util.*

fun Application.configureAuthentication() {

    install(Authentication) {
        jwt("auth-jwt") {
            realm = JwtSettings.realm
            verifier(JWT
                .require(Algorithm.HMAC256(JwtSettings.secret))
                .withAudience(JwtSettings.audience)
                .withIssuer(JwtSettings.issuer)
                .build())
            validate { credential ->
                JWTPrincipal(credential.payload)
            }
        }
    }
}