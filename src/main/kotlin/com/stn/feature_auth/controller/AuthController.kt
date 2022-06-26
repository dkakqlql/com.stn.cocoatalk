package com.stn.feature_auth.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.stn.feature_auth.data.UserDataSource
import com.stn.feature_auth.data.entity.User
import com.stn.feature_auth.util.JwtSettings
import org.litote.kmongo.util.idValue
import java.util.*

class AuthController(
    private val userDataSource: UserDataSource
) {

    suspend fun insertUser(user: User) {
        if (userDataSource.findUserByEmail(user.email) is User) {
            throw AlreadyExistUser()
        } else {
            userDataSource.insertUser(User(
                username = user.username,
                email = user.email,
                password = user.password
            ))
        }
    }

    suspend fun verifyUser(email: String): User {
        val user = userDataSource.findUserByEmail(email)
        return User(
            id = user?.id ?: "",
            username = user?.username ?: "",
            email = user?.email ?: "",
            password = user?.password ?: "",
            chatlist = user?.chatlist ?: emptyList()
        )
    }
}

class DismatchedUserCredentialException: Exception(
    "비밀번호가 일치하지 않습니다."
)

class NonExistUser: Exception(
    "존재하지 않는 유저입니다."
)

class AlreadyExistUser: Exception(
    "이미 존재하는 유저입니다."
)