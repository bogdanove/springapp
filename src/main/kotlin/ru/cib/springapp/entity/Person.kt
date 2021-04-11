package ru.cib.springapp.entity


import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.util.*
import javax.persistence.*

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property="@id", scope = Person::class)
@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    var name: String? = null

    var birthday: Date? = null

    var account: Long? = null

    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var hobbies: MutableList<Hobby>? = null

    override fun toString(): String {
        return "Id: $id, Name: $name, Birthday: $birthday, account: $account, Hobby: $hobbies"
    }
}