package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person

@EnableRabbit
@Component
class Consumer {

    @RabbitListener(queues = ["queue"])
    fun consumeMessageFirst(person: Person): Person {
        person.account = System.currentTimeMillis()
        return person
    }
}