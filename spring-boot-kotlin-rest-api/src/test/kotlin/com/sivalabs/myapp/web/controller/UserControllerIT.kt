package com.sivalabs.myapp.web.controller

import com.sivalabs.myapp.entity.User
import com.sivalabs.myapp.repo.UserRepository
import com.sivalabs.myapp.utils.BaseIntegrationTest
import com.sivalabs.myapp.utils.TestHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK

class UserControllerIT : BaseIntegrationTest() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    lateinit var existingUser: User
    lateinit var newUser: User
    lateinit var updateUser: User

    @Before
    fun setUp() {
        newUser = TestHelper.buildUser()

        existingUser = TestHelper.buildUser()
        existingUser = userRepository.save(existingUser)

        updateUser = TestHelper.buildUser()
        updateUser = userRepository.save(updateUser)
    }

    @After
    fun tearDown() {
        if (newUser.id != 0L) {
            userRepository.deleteById(newUser.id)
        }
        userRepository.deleteAll(userRepository.findAllById(asList(existingUser.id, updateUser.id)))
    }

    @Test
    fun should_get_all_users() {
        val responseEntity = restTemplate.getForEntity("/api/users", Array<User>::class.java)
        val users = asList(responseEntity.body)
        assertThat(users).isNotEmpty
    }

    @Test
    fun should_get_user_by_id() {
        val responseEntity = restTemplate.getForEntity("/api/users/${existingUser.id}", User::class.java)
        val user = responseEntity.body
        assertThat(user).isNotNull
    }

    @Test
    fun should_create_user() {
        val request = HttpEntity(newUser)
        val responseEntity = restTemplate.postForEntity("/api/users", request, User::class.java)
        val savedUser = responseEntity.body!!
        assertThat(savedUser.id).isNotNull()
    }

    @Test
    fun should_update_user() {
        val request = HttpEntity(updateUser)
        restTemplate.put("/api/users/${updateUser.id}", request, User::class.java)
        val responseEntity = restTemplate.getForEntity("/api/users/${updateUser.id}", User::class.java)
        val updatedUser = responseEntity.body!!
        assertThat(updatedUser.id).isEqualTo(updateUser.id)
        assertThat(updatedUser.email).isEqualTo(updateUser.email)
    }

    @Test
    fun should_delete_user() {
        var responseEntity = restTemplate.getForEntity("/api/users/${existingUser.id}", User::class.java)
        assertThat(responseEntity.statusCode).isEqualTo(OK)
        restTemplate.delete("/api/users/${existingUser.id}")
        responseEntity = restTemplate.getForEntity("/api/users/${existingUser.id}", User::class.java)
        assertThat(responseEntity.statusCode).isEqualTo(NOT_FOUND)
    }
}
