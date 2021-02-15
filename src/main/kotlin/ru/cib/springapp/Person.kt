package ru.cib.springapp




import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.*
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "person")
class Person(
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = 0,

    @XmlElement
    var name: String? = null,

    @XmlJavaTypeAdapter(DateAdapter::class)
    var birthday: Date? = null,

    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true)
    var hobbies: MutableList<Hobby>? = null

) {


    override fun toString(): String {
        return "Id: $id, Name: $name, Birthday: $birthday, Hobby: $hobbies"
    }
}