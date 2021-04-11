package ru.cib.springapp.entity


import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator::class, property="@id", scope = Hobby::class)
@Entity
@Table(name = "hobby")
class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0


    var complexity: Int? = 0

    var hobby_name: String? = "name"

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    var person: Person? = null

    override fun toString(): String {
        return "id: $id, complexity: $complexity, hobby name is: $hobby_name"
    }
}