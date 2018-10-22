package com.sivalabs.myapp.web.controller

import com.sivalabs.myapp.entity.User
import com.sivalabs.myapp.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    private val log = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("")
    fun users(): List<User> {
        log.info("process=get-users")
        return userService.getAllUsers()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User> {
        log.info("process=get-user, user_id={}", id)
        val user = userService.getUserById(id)
        return user.map { u -> ResponseEntity.ok(u) }
                .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    fun createUser(@RequestBody user: User): User {
        log.info("process=create-user, user_email={}", user.email)
        return userService.createUser(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): User {
        log.info("process=update-user, user_id={}", id)
        user.id = id
        return userService.updateUser(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        log.info("process=delete-user, user_id={}", id)
        userService.deleteUser(id)
    }

}
