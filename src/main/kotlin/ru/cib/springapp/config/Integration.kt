package ru.cib.springapp.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.file.dsl.Files
import ru.cib.springapp.service.PersonService
import java.io.File


class Integration {

    @Configuration
    class ChannelsConfiguration {

        @Bean
        fun runing(): DirectChannel = MessageChannels.direct().get()

        @Bean
        fun errorsOff(): DirectChannel = MessageChannels.direct().get()
    }

    @Configuration
    class FileConfiguration(
            private val channels: ChannelsConfiguration,
            private val personService: PersonService
    ) {

        private val input = File("src/main/resources/File/in")
        private val output = File("src/main/resources/File/out")
        private val errors = File("src/main/resources/File/errors")


        @Bean
        fun filesFlow() = integrationFlow(
                Files.inboundAdapter(this.input)
                        .autoCreateDirectory(true)
                        .preventDuplicates(false),
                { poller { it.fixedDelay(2000).maxMessagesPerPoll(1) } }
        ) {

            filter<File> {
                (it.isFile) && (it.extension.toLowerCase() == "xml")
            }

            route<File> {

                when (personService.savePersonToDBFromXML(it)) {
                    true -> channels.runing()
                    else -> channels.errorsOff()
                }
            }
        }

        @Bean
        fun fileToDb() = integrationFlow(channels.runing()) {
            handle(Files.outboundAdapter(output)
                    .deleteSourceFiles(true)
                    .autoCreateDirectory(true)
            )
        }

        @Bean
        fun errFlow() = integrationFlow(channels.errorsOff()) {
            handle(Files.outboundAdapter(errors)
                    .autoCreateDirectory(true)
                    .deleteSourceFiles(true)
            )
        }
    }
}