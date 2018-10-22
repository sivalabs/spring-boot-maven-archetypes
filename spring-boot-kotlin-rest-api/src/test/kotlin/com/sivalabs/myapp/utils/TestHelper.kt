package com.sivalabs.myapp.utils

import com.sivalabs.myapp.entity.User

import java.util.Random
import java.util.UUID

import java.lang.String.format

object TestHelper {
    fun buildUser(): User {
        val uuid = UUID.randomUUID().toString()
        var user = User()
        user.name = "name-$uuid"
        user.email = "someone-$uuid@gmail.com"

        return user
    }

    fun buildUserWithId(): User {
        val random = Random()
        val uuid = UUID.randomUUID().toString()
        var user = User()
        user.id = random.nextLong()
        user.name = "name-$uuid"
        user.email = "someone-$uuid@gmail.com"

        return user
    }
}
