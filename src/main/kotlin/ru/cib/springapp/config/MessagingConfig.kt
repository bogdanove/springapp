package ru.cib.springapp.config


import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfig {

    val queue: String = "queue"
    //val queueResponse: String = "queue_response"
    val exchange: String = "exchange"
    val routingKey: String = "routingKey"


    @Bean
    fun queue() = Queue(queue)

    @Bean
    fun exchange() = TopicExchange(exchange)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange) = BindingBuilder
            .bind(queue)
            .to(exchange)
            .with(routingKey)

    @Bean
    fun converters() = Jackson2JsonMessageConverter()

    @Bean
    fun template(connectionFactory: org.springframework.amqp.rabbit.connection.ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = converters()
        return rabbitTemplate
    }

}