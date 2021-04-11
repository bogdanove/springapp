package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person
import ru.cib.springapp.service.PersonController

@EnableRabbit
@Component
class Consumer(var personController: PersonController) {

    @RabbitListener(queues = ["queue"])
    fun consumeMessage(person: Person) {
        personController.save(person)
    }
}