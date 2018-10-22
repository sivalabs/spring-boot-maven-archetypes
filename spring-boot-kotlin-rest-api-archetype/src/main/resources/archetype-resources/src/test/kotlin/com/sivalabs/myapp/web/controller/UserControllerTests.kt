package com.sivalabs.myapp.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.sivalabs.myapp.entity.User
import com.sivalabs.myapp.service.UserService
import com.sivalabs.myapp.utils.TestHelper
import com.sivalabs.myapp.utils.any
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import java.util.Arrays
import java.util.Optional

import org.mockito.BDDMockito.given
import org.mockito.Mockito.doNothing
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [UserController::class])
class UserControllerTests {

    @MockBean
    lateinit var userService: UserService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private lateinit var existingUser: User
    private lateinit var newUser: User
    private lateinit var updateUser: User

    @Before
    fun setUp() {
        newUser = TestHelper.buildUserWithId()
        existingUser = TestHelper.buildUserWithId()
        updateUser = TestHelper.buildUserWithId()
    }

    @Test
    fun should_get_all_users() {
        given(userService.getAllUsers()).willReturn(Arrays.asList(existingUser, updateUser))

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
    }

    @Test
    fun should_get_user_by_id() {
        given(userService.getUserById(existingUser.id)).willReturn(Optional.of(existingUser))

        this.mockMvc
                .perform(get("/api/users/" + existingUser.id))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(existingUser.id)))
                .andExpect(jsonPath("$.name", `is`(existingUser.name)))
                .andExpect(jsonPath("$.email", `is`(existingUser.email)))
    }

    @Test
    fun should_create_user() {
        given(userService.createUser(newUser)).willReturn(newUser)

        this.mockMvc
                .perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser))
                )
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", `is`(newUser.name)))
                .andExpect(jsonPath("$.email", `is`(newUser.email)))
    }

    @Test
    fun should_update_user() {
        given(userService.updateUser(existingUser)).willReturn(existingUser)

        this.mockMvc
                .perform(put("/api/users/" + existingUser.id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingUser))
                )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(existingUser.id)))
                .andExpect(jsonPath("$.name", `is`(existingUser.name)))
                .andExpect(jsonPath("$.email", `is`(existingUser.email)))
    }

    @Test
    fun should_delete_user() {
        doNothing().`when`<UserService>(userService).deleteUser(existingUser.id)

        this.mockMvc
                .perform(delete("/api/users/" + existingUser.id))
                .andExpect(status().isOk)
    }

}
