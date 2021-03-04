package ru.cib.springapp.service

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ru.cib.springapp.entity.Person
import ru.cib.springapp.repository.PersonRepository


@Service
class PersonController(var personRepository: PersonRepository) {


    fun saveAll(persons: List<Person>) {
        personRepository.saveAll(persons)
    }


    @Bean
    fun savePersonToDBFromXML() {
        saveAll(SpringJAXBConverter().xmlToObject("src/main/resources/File/file.xml"))
    }

    fun getAll(): List<Person> {
       return personRepository.findAll()
    }

//    @Bean
//    fun savePersonListToXML() {
//        var persons = Persons()
//        persons.persons = getAll()
//        SpringJAXBConverter().objectToXML("src/main/resources/File/XML.xml", persons)
//    }

}