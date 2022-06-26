package com.stn.feature_auth.data

import com.stn.feature_auth.data.entity.User

interface UserDataSource {

    suspend fun findUserByEmail(email: String): User?

    suspend fun insertUser(user: User)

}