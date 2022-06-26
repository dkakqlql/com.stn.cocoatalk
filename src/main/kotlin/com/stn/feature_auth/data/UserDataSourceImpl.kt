package com.stn.feature_auth.data

import com.stn.feature_auth.controller.AlreadyExistUser
import com.stn.feature_auth.data.entity.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserDataSourceImpl(
    private val db: CoroutineDatabase
): UserDataSource {

    private val users = db.getCollection<User>()

    override suspend fun findUserByEmail(email: String): User? {
        return users.findOne(User::email eq email)
    }

    override suspend fun insertUser(user: User) {
        users.insertOne(user)
    }

}