package ru.cib.springapp.entity






import java.util.*
import javax.persistence.*





@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    var name: String? = null


    var birthday: Date? = null


    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var hobbies: MutableList<Hobby>? = null


    override fun toString(): String {
        return "Id: $id, Name: $name, Birthday: $birthday, Hobby: $hobbies"
    }
}