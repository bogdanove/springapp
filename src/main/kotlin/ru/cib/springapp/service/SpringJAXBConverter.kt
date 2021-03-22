package ru.cib.springapp.service


import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.cib.springapp.entity.Person
import ru.cib.springapp.model.PersonXml
import ru.cib.springapp.model.PersonsXml
import ru.cib.springapp.utils.toPerson
import ru.cib.springapp.utils.toPersonXml
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import javax.xml.bind.JAXBContext
import javax.xml.transform.stream.StreamResult

@Service("converter")
class SpringJAXBConverter {


    private val context = JAXBContext.newInstance(PersonsXml::class.java)

    @Throws(IOException::class)
    fun objectToXML(fileName: String, persons: PersonsXml) {

        val marshaller = context.createMarshaller()
        val fos = FileOutputStream(fileName)
            marshaller?.marshal(persons, StreamResult(fos))

    }

    fun xmlToObject(fileName: String): List<Person> {
        val un = context.createUnmarshaller()

        val list = mutableListOf<Person>()

        val personFromFile = un.unmarshal(FileReader("src/main/resources/File/in/file.xml")) as PersonsXml
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