package ru.cib.springapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.cib.springapp.entity.Person

interface PersonRepository: JpaRepository<Person, Long> {

    fun findByAccountIsNull(): MutableList<Person>

}