package ru.cib.springapp.entity

import ru.cib.springapp.entity.Person
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Persons{

    @XmlElement(name ="person")
    var persons: List<Person>? = null


}