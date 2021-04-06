package ru.cib.springapp.model


import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType


@XmlAccessorType(XmlAccessType.FIELD)
class HobbyXml {

    var complexity: Int? = 0

    var hobby_name: String? = "name"

    override fun toString(): String {
        return "complexity: $complexity, hobby name is: $hobby_name"
    }
}