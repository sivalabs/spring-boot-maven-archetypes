package com.sivalabs.myapp.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
    @GeneratedValue(generator = "user_id_generator")
    var id: Long = 0

    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false, unique = true)
    var email: String = ""

    @JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @JsonProperty("updated_at")
    var updatedAt: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

}
