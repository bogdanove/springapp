package ru.cib.springapp.service


import org.springframework.stereotype.Service
import ru.cib.springapp.entity.Person
import ru.cib.springapp.model.PersonsXml
import ru.cib.springapp.utils.toPerson
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import javax.xml.bind.JAXBContext
import javax.xml.transform.stream.StreamResult

@Service
class JAXBConverter {

    private val context = JAXBContext.newInstance(PersonsXml::class.java)

    fun objectToXML(fileName: String, persons: PersonsXml) {

        val marshaller = context.createMarshaller()
        val fos = FileOutputStream(fileName)
        marshaller?.marshal(persons, StreamResult(fos))

    }

    fun xmlToObject(file: File): List<Person> {
        val un = context.createUnmarshaller()

        val list = mutableListOf<Person>()

        val personFromFile = un.unmarshal(FileReader(file.absolutePath)) as PersonsXml
        personFromFile.persons?.forEach { person ->
            list.add(person.toPerson())
            list.forEach { persondb ->
                persondb.hobbies?.forEach {
                    it.person = persondb
                }
            }
        }
        return list
    }
}