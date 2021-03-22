package ru.cib.springapp.entity


import javax.persistence.*



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