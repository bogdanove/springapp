package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import ru.cib.springapp.service.PersonController

class MessageController(
        var template: RabbitTemplate,
        var personController: PersonController
) {
    @Bean
    fun sendMessage(): String {
        val person = personController.findNullAccount()
        person.account = System.currentTimeMillis().toInt()
        template.convertAndSend(person)
        return "Done!"
    }
}