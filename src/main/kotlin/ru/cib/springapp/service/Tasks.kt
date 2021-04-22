package ru.cib.springapp.service

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person
import ru.cib.springapp.repository.PersonRepository


@Component
class Tasks(
       private val template: RabbitTemplate,
       private val personRepository: PersonRepository
) {

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    fun checkingSendAndReceive() {
        personRepository.findByAccountIsNull()?.forEach {
            sendMessage(it)
            Thread.sleep(2432)
        }
    }

    fun sendMessage(person: Person): String {
        template.convertAndSend("queue_response", person) as Person
        return "Done!"
    }
}