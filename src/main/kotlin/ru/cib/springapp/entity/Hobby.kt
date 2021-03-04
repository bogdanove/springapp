package ru.cib.springapp.entity


import javax.persistence.*
import javax.xml.bind.annotation.*

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "hobby")
class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient var id: Long = 0

    @XmlTransient
    @Column(name = "person_id")
    var personId: Long? = null

    var complexity: Int = 0

    var hobby_name: String = "name"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    @XmlTransient
    val person: Person? = null

    override fun toString(): String {
        return "id: $id, complexity: $complexity, hobby name is: $hobby_name"
    }
}