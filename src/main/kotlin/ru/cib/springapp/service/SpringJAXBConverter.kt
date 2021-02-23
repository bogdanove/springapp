package ru.cib.springapp.service


import ru.cib.springapp.entity.Person
import ru.cib.springapp.entity.Persons
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import javax.xml.bind.JAXBContext
import javax.xml.transform.stream.StreamResult


class SpringJAXBConverter() {


    private val context = JAXBContext.newInstance(Persons::class.java)

    @Throws(IOException::class)
    fun objectToXML(fileName: String, persons: Persons) {

        val marshaller = context.createMarshaller()
        val fos: FileOutputStream = FileOutputStream(fileName)
            marshaller?.marshal(persons, StreamResult(fos))

    }

    fun xmlToObject(fileName: String): List<Person> {
        val un = context.createUnmarshaller()

        val list = mutableListOf<Person>()

        val persFromFile = un.unmarshal(FileReader("src/main/resources/File/file.xml")) as Persons
        persFromFile.persons?.forEach {
            list.add(it)

        }
        return list
    }


}