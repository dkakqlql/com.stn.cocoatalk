package com.stn.feature_auth.util

object JwtSettings {
    const val secret = "secret"
    const val issuer = "http://172.30.1.11:8080/"
    const val audience = "http://172.30.1.11:8080/token"
    const val realm = "Access to 'token'"
}
