//package ru.cib.springapp.service
//
//
//
//import org.springframework.context.annotation.Bean
//import org.springframework.jdbc.core.BeanPropertyRowMapper
//import org.springframework.jdbc.core.JdbcTemplate
//import org.springframework.jdbc.support.GeneratedKeyHolder
//import org.springframework.stereotype.Service
//import ru.cib.springapp.entity.Hobby
//import Person
//import ru.cib.springapp.model.Persons
//
//
//@Service
//class SpringJDBCConverter(
//        var jdbcTemplate: JdbcTemplate
//) {
//
//
////    @Bean
////    fun be() {
////        //writeData(person)
////        //writeDataWithHobby(CreatePerson().list)
////        println((fetchData(indexPerson(), indexHobby()))).toString()
////    }
//
//
//    fun writeData(person: Person) {
//        jdbcTemplate.update("insert into person(id, name, birthday) values(default, ?, ?)", person.name, java.sql.Date(person.birthday!!.time))
//    }
//
//    fun writeDataWithHobby(list: List<Person>) {
//        val INSERT_SQL = "insert into person(id, name, birthday) values(default, ?, ?)"
//
//
//        list.forEach { person ->
//            val keyHolder = GeneratedKeyHolder()
//            jdbcTemplate.update({
//                it.prepareStatement(INSERT_SQL, arrayOf("id")).apply {
//                    setString(1, person.name)
//                    setDate(2, java.sql.Date(person.birthday!!.time))
//                }
//            }, keyHolder)
//
//            val id = keyHolder.key
//            person.hobbies?.forEach {
//                jdbcTemplate.update("insert into hobby(id, person_id, complexity, hobby_name) values(default, ?, ?, ?)",
//                        id, it.complexity, it.hobby_name)
//            }
//        }
//
//
//
//    }
//
//
//
//
//    fun writeDataWithHobby(person: Person) {
//        val INSERT_SQL = "insert into person(id, name, birthday) values(default, ?, ?)"
//        val keyHolder = GeneratedKeyHolder()
//        jdbcTemplate.update({
//            it.prepareStatement(INSERT_SQL, arrayOf("id")).apply {
//                setString(1, person.name)
//                setDate(2, java.sql.Date(person.birthday!!.time))
//            }
//        }, keyHolder)
//        val id = keyHolder.key
//        person.hobbies?.forEach {
//            jdbcTemplate.update("insert into hobby(id, person_id, complexity, hobby_name) values(default, ?, ?, ?)",
//                    id, it.complexity, it.hobby_name)
//        }
//
//    }
//
//    fun indexPerson(): List<Person> {
//        return jdbcTemplate.query("SELECT * FROM person", BeanPropertyRowMapper(Person::class.java))
//    }
//
//    fun indexHobby(): List<Hobby> {
//        return jdbcTemplate.query("SELECT * FROM hobby", BeanPropertyRowMapper(Hobby::class.java))
//    }
//
//    fun fetchData(personList: List<Person>, hobbyList: List<Hobby>): List<Person> {
//        personList?.forEach { person ->
//            val hobbylist = mutableListOf<Hobby>()
//            hobbyList?.forEach { hobby ->
//                if (person.id == hobby.personId) {
//                    hobbylist.add(hobby)
//                }
//            }
//            person.hobbies = hobbylist
//        }
//        return personList
//    }
//
//    @Bean
//    fun convertObjToXML() {
//        var persons = Persons()
//
//        persons.persons = fetchData(indexPerson(), indexHobby())
//        SpringJAXBConverter().objectToXML("src/main/resources/File/XML.xml", persons)
//    }
//
//    @Bean
//    fun convertXMLToObj() {
//        SpringJAXBConverter().xmlToObject("src/main/resources/File/XML.xml")
//    }
//
//    @Bean
//    fun be() {
//        writeDataWithHobby(SpringJAXBConverter().xmlToObject("src/main/resources/File/XML.xml"))
//
//    }
//
//}
//
//
//
//
//
//
