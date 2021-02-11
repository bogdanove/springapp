package ru.cib.springapp


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import ru.cib.Hobby
import ru.cib.Person
import ru.cib.Persons
import javax.sql.DataSource


@SpringBootApplication
class SpringappApplication


    fun main(args: Array<String>) {
        runApplication<SpringappApplication>(*args)

    }





