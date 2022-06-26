package com.stn.feature_chat.room

class MemberAlreadyExistsException: Exception(
    "채팅방에 해당 이름을 가진 멤버가 이미 있습니다."
) {
}