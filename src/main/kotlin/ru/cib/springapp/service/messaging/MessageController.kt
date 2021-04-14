package ru.cib.springapp.service.messaging

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import ru.cib.springapp.entity.Person
import ru.cib.springapp.service.PersonController


@Component
class MessageController(
        var template: RabbitTemplate,
        var personController: PersonController
) {

    @Bean
    fun checkingSendAndReceive() {
        personController.findNullAccount()?.forEach {
            sendMessage(it)
            Thread.sleep(2432)
        }
    }

    fun sendMessage(person: Person): String {
        println(person)
        template.convertSendAndReceive(MessagingConfig().exchange, MessagingConfig().routing_key, person)
        //personController.save(personUpd)
        return "Done!"
    }
}