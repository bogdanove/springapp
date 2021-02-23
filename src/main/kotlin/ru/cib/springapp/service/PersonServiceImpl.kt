package ru.cib.springapp.service

import org.springframework.beans.factory.annotation.Autowired
import ru.cib.springapp.entity.Person
import ru.cib.springapp.repository.PersonRepository

class PersonServiceImpl(@Autowired var personRepository: PersonRepository): PersonService {



    override fun save(person: Person) {
        personRepository.save(person)
    }

    override fun getAll(): List<Person> {
       return personRepository.findAll()
    }

    override fun saveAll(persons: List<Person>) {
        personRepository.saveAll(persons)
    }
}