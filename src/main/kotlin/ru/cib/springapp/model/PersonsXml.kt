package ru.cib.springapp.model


import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
class PersonsXml {

    @XmlElement(name = "person")
    var persons: MutableList<PersonXml>? = null
}