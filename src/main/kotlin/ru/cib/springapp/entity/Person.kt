package ru.cib.springapp.entity




import org.springframework.data.jpa.domain.AbstractPersistable_.id
import ru.cib.springapp.entity.Hobby
import ru.cib.springapp.service.DateAdapter
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.*
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    var id: Long? = 0

    @XmlElement
    var name: String? = null

    @XmlJavaTypeAdapter(DateAdapter::class)
    var birthday: Date? = null

    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var hobbies: MutableList<Hobby>? = null


    override fun toString(): String {
        return "Id: $id, Name: $name, Birthday: $birthday, Hobby: $hobbies"
    }
}