package ru.cib.springapp


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class SpringappApplication


fun main(args: Array<String>) {
    runApplication<SpringappApplication>(*args)
}





