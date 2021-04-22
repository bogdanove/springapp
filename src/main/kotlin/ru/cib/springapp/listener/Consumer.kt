package ru.cib.springapp.listener

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person
import ru.cib.springapp.repository.PersonRepository

@EnableRabbit
@Component
class Consumer(
        private val personRepository: PersonRepository
) {

    @RabbitListener(queues = ["queue_response"])
    fun consumeMessageFirst(person: Person) {
        personRepository.save(person)
    }
}