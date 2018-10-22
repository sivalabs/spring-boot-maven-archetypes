package com.sivalabs.myapp.service

import com.sivalabs.myapp.entity.User
import com.sivalabs.myapp.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun updateUser(user: User): User {
        return userRepository.save(user)
    }

    fun deleteUser(userId: Long) {
        userRepository.deleteById(userId)
    }
}
