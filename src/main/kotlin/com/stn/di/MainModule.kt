package com.stn.di

import com.stn.feature_auth.controller.AuthController
import com.stn.feature_auth.data.UserDataSource
import com.stn.feature_auth.data.UserDataSourceImpl
import com.stn.feature_chat.data.MessageDataSource
import com.stn.feature_chat.data.MessageDataSourceImpl
import com.stn.feature_chat.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("cocoa_db")
    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
    single {
        RoomController(get(), get())
    }
    single {
        AuthController(get())
    }
}