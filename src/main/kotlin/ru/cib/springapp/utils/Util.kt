@file:Suppress("UNUSED_VALUE")

package ru.cib.springapp.utils


import ru.cib.springapp.entity.Hobby
import ru.cib.springapp.entity.Person
import ru.cib.springapp.model.HobbyXml
import ru.cib.springapp.model.PersonXml


fun HobbyXml.toHobby() = Hobby().let {
    it.complexity = this.complexity
    it.hobby_name = this.hobby_name
    it
}


fun PersonXml.toPerson() = Person().let {
    it.name = this.name
    it.birthday = this.birthday
    var listHobbies = mutableListOf<Hobby>()
    this.hobbies?.forEach { hobbyXml ->
        listHobbies.add(hobbyXml.toHobby())
    }
    it.hobbies = listHobbies
    it
}


fun Hobby.toHobbyXml() = HobbyXml().let {
    it.complexity = this.complexity
    it.hobby_name = this.hobby_name
    it
}

fun Person.toPersonXml() = PersonXml().let {
    it.name = this.name
    it.birthday = this.birthday
    var listHobbies = mutableListOf<HobbyXml>()
    this.hobbies?.forEach { hobby ->
        listHobbies.add(hobby.toHobbyXml())
    }
    it.hobbies = listHobbies
    it
}

