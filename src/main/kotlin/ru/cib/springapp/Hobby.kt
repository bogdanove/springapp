package ru.cib.springapp

import javax.persistence.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlTransient

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "hobby")
class Hobby(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlTransient var id: Long = 0,

    @XmlTransient var personId: Long = 0,
    var complexity: Int = 0,

    var hobby_name: String = "name",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    val person: Person
) {
    constructor() : this() {

    }


    override fun toString(): String {
        return "id: $id, personId: $personId, complexity: $complexity, hobby name is: $hobby_name"
    }
}