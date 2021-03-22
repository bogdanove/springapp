package ru.cib.springapp.service

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ru.cib.springapp.entity.Person
import ru.cib.springapp.entity.Persons
import ru.cib.springapp.model.PersonsXml
import ru.cib.springapp.repository.PersonRepository
import ru.cib.springapp.utils.toPersonXml


@Service("db")
class PersonController(
        var personRepository: PersonRepository,
        var springJAXBConverter: SpringJAXBConverter
        ) {


    fun saveAll(persons: List<Person>): String {
        personRepository.saveAll(persons)

        return "Done"
    }


//    @Bean
//    fun savePersonToDBFromXML() {
//        saveAll(springJAXBConverter.xmlToObject("src/main/resources/File/file.xml"))
//    }

    fun getAll(): MutableList<Person> {
       return personRepository.findAll()
    }

//    @Bean
//    fun savePersonListToXML() {
//        var personsdb = PersonsXml()
//        var persons = Persons()
//        persons.persons = getAll()
//        persons.persons?.forEach {
//            personsdb.persons?.add(it.toPersonXml())
//        }
//        springJAXBConverter.objectToXML("src/main/resources/File/XML.xml", personsdb)
//    }

}