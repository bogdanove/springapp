package ru.cib.springapp.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BasePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = 0
}