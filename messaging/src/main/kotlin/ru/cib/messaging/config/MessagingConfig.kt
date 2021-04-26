package ru.cib.messaging.config


import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfig {

    val queueRequest: String = "queue_request"
    val exchange: String = "exchange"
    val routingKey: String = "routingKey"


    @Bean
    fun queueRequest() = Queue(queueRequest)

    @Bean
    fun exchange() = TopicExchange(exchange)

    @Bean
    fun bindingRequest(queueRequest: Queue, exchange: TopicExchange): Binding? = BindingBuilder
            .bind(queueRequest)
            .to(exchange)
            .with(routingKey)

    @Bean
    fun converters() = Jackson2JsonMessageConverter()

    @Bean
    fun template(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = converters()
        return rabbitTemplate
    }

}