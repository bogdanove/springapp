package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person


@Component
class MessageController(
        var template: RabbitTemplate,
) {

    fun sendMessage(person: Person): String {
        person.account = System.currentTimeMillis()
        template.convertAndSend(MessagingConfig().exchange, MessagingConfig().routing_key, person)
        return "Done!"
    }
}