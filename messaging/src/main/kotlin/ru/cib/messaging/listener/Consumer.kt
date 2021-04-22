package ru.cib.messaging.listener

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import ru.cib.messaging.entity.Person


@EnableRabbit
@Component
class Consumer(
        private val template: RabbitTemplate
) {

    @RabbitListener(queues = ["queue_request"])
    fun consumeMessageSecond(person: Person) {
        person.account = System.currentTimeMillis()
        template.convertAndSend("queue_response", person)
    }
}