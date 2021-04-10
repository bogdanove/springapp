package ru.cib.springapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import ru.cib.springapp.entity.Person

interface PersonRepository: JpaRepository<Person, Long> {

    fun findByAccountIsNull(): Person

//    @Modifying
//    @Query("update Person p set p.account = ?1 where p.id = ?2")
//    fun setUserInfoById(account: Int, id: Long);
}