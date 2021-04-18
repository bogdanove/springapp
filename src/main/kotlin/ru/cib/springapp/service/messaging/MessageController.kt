package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person
import ru.cib.springapp.service.PersonController


@Component
class MessageController(
        var template: RabbitTemplate,
        var personController: PersonController
) {

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    fun checkingSendAndReceive() {
        personController.findNullAccount()?.forEach {
            sendMessage(it)
            Thread.sleep(2432)
        }
    }

    fun sendMessage(person: Person): String {
        val personUpd = template.convertSendAndReceive("queue", person) as Person
        personController.save(personUpd)
        return "Done!"
    }
}