package ru.cib.springapp.service


import org.springframework.stereotype.Service
import ru.cib.springapp.repository.PersonRepository
import java.io.File


@Service
class PersonService(
        private val personRepository: PersonRepository,
        private val JAXBConverter: JAXBConverter
) {

    fun savePersonToDBFromXML(file: File): Boolean {

        return try {
            personRepository.saveAll(JAXBConverter.xmlToObject(file))
            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}