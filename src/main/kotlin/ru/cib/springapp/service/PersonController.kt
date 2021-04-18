package ru.cib.springapp.service


import org.springframework.stereotype.Service
import ru.cib.springapp.entity.Person
import ru.cib.springapp.repository.PersonRepository
import java.io.File


@Service("db")
class PersonController(
        var personRepository: PersonRepository,
        var springJAXBConverter: SpringJAXBConverter
) {

    fun saveAll(persons: List<Person>) {
        personRepository.saveAll(persons)
    }

    fun save(person: Person) {
        personRepository.save(person)
    }

    fun savePersonToDBFromXML(file: File): Boolean {

        return try {
            saveAll(springJAXBConverter.xmlToObject(file))
            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun findNullAccount(): MutableList<Person> {
        return personRepository.findByAccountIsNull()
    }


    fun getAll(): MutableList<Person> {
        return personRepository.findAll()
    }

}