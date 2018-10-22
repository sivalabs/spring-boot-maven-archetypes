package com.sivalabs.myapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinBasicApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinBasicApplication>(*args)
}
