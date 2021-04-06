package ru.cib.springapp.service


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.file.dsl.Files
import java.io.File


class Intergration {

    @Configuration
    class ChannelsConfiguration {

        @Bean
        fun run() = MessageChannels.direct().get()

        @Bean
        fun errors() = MessageChannels.direct().get()
    }

    @Configuration
    class FileConfiguration(private val channels: ChannelsConfiguration,
                            var personController: PersonController) {

        private val input = File("src/main/resources/File/in")
        private val output = File("src/main/resources/File/out")
        private val errors = File("src/main/resources/File/errors")


        @Bean
        fun filesFlow() = integrationFlow(
                Files.inboundAdapter(this.input).autoCreateDirectory(true).preventDuplicates(false),
                { poller { it.fixedDelay(2000).maxMessagesPerPoll(1) } }
        ) {

            filter<File> {
                (it.isFile) && (it.extension.toLowerCase() == "xml")
            }

            route<File> {

                when (personController.savePersonToDBFromXML(it)) {
                    true -> channels.run()
                    else -> channels.errors()
                }
            }
        }

        @Bean
        fun fileToDb() = integrationFlow(channels.run()) {
            handle(Files.outboundAdapter(output).deleteSourceFiles(true).autoCreateDirectory(true))
        }

        @Bean
        fun errFlow() = integrationFlow(channels.errors()) {
            handle(Files.outboundAdapter(errors).autoCreateDirectory(true).deleteSourceFiles(true))
        }
    }
}