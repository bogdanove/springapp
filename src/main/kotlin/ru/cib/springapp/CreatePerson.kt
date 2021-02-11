package ru.cib.springapp

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import ru.cib.Hobby
import ru.cib.Person
import ru.cib.Persons
import java.util.*


class CreatePerson {

    val fileName = "src/main/resources/File/file.xml"
    val fileName1 = "src/main/resources/File/file1.xml"

    val hobby = Hobby(1, 1, "Walking")
    val hobby1 = Hobby(2, 44, "RunRunRun")
    val hobby2 = Hobby(2, 55, "FastRunForest")


    val hobbies0 = listOf(hobby)
    val hobbies1 = listOf(hobby1, hobby2)
    val person = Person().apply {

        name = "Spring Swing String"
        birthday = GregorianCalendar(2002, 10, 10).time

        hobbies = hobbies0
    }
    val person1 = Person().apply {

        name = "Akop Kolopop No"
        birthday = GregorianCalendar(2002, 10, 10).time

        hobbies = hobbies1
    }
    val list = listOf(person, person1)







}