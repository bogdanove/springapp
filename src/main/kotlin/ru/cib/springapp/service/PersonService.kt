package ru.cib.springapp.service

import ru.cib.springapp.entity.Person

interface PersonService {
   fun save(person: Person)

   fun getAll(): List<Person>

   fun saveAll(persons: List<Person>)
}