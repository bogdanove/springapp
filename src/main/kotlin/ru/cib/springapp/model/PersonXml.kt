package ru.cib.springapp.model


import ru.cib.springapp.utils.DateAdapter
import java.util.*
import javax.xml.bind.annotation.*
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
class PersonXml {


    @XmlElement
    var name: String? = null

    @XmlJavaTypeAdapter(DateAdapter::class)
    var birthday: Date? = null

    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    var hobbies: MutableList<HobbyXml>? = null


    override fun toString(): String {
        return "Name: $name, Birthday: $birthday, Hobby: $hobbies"
    }
}