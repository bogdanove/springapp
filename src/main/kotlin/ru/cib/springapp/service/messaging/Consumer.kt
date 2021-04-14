package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person
import ru.cib.springapp.service.PersonController

@EnableRabbit
@Component
class Consumer {

    @RabbitListener(queues = ["queue"])
    fun consumeMessageFirst(person: Person): Person {
        person.account = System.currentTimeMillis()
        println("$person + Queue one")
        return person
    }

    @RabbitListener(queues = ["queue"])
    fun consumeMessageSecond(person: Person): Person {
        person.account = System.currentTimeMillis()
        println("$person + Queue two")
        return person
    }
}