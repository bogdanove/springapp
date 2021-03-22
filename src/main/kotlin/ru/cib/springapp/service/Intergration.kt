package ru.cib.springapp.service


import jdk.internal.org.xml.sax.ErrorHandler
import org.hibernate.cfg.JPAIndexHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.BaseIntegrationFlowDefinition
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.endpoint.AbstractEndpoint
import org.springframework.integration.file.dsl.Files
import org.springframework.integration.file.support.FileExistsMode
import java.io.File
import javax.xml.ws.Endpoint


//@Configuration
//class ChannelsConfiguration {
//
//    @Bean
//    fun txt() = MessageChannels.direct().get()
//
//    @Bean
//    fun csv() = MessageChannels.direct().get()
//
//    @Bean
//    fun errors() = MessageChannels.direct().get()
//}
//
//@Configuration
//class FileConfiguration(private val channels: ChannelsConfiguration) {
//
//    private val input = File("${System.getenv("HOME")}/Desktop/in")
//    private val output = File("${System.getenv("HOME")}/Desktop/out")
//    private val csv = File(output, "csv")
//    private val txt = File(output, "txt")
//
//    @Bean
//    fun filesFlow() = integrationFlow(
//            Files.inboundAdapter(this.input).autoCreateDirectory(true),
//            { poller { it.fixedDelay(500).maxMessagesPerPoll(1) } }
//    ) {
//
//        filter<File> { it.isFile }
//        route<File> {
//            when (it.extension.toLowerCase()) {
//                "csv" -> channels.csv()
//                "txt" -> channels.txt()
//                else -> channels.errors()
//            }
//        }
//    }
//
//    @Bean
//    fun csvFlow() = integrationFlow(channels.csv()) {
//        handle(Files.outboundAdapter(csv).autoCreateDirectory(true))
//    }
//
//    @Bean
//    fun txtFlow() = integrationFlow(channels.txt()) {
//        handle(Files.outboundAdapter(txt).autoCreateDirectory(true))
//    }
//}








class Intergration {

    @Configuration
    class ChannelsConfiguration {

        @Bean
        fun xml() = MessageChannels.direct().get()

        @Bean
        fun run() = MessageChannels.direct().get()

        @Bean
        fun errors() = MessageChannels.direct().get()
    }

    @Configuration
    class FileConfiguration(private val channels: ChannelsConfiguration) {

        private val input = File("src/main/resources/File/in")
        private val output = File("src/main/resources/File/out")
        private val errors = File("src/main/resources/File/errors")
        //private val xml = File(output, "xml")


        @Bean
        fun filesFlow() = integrationFlow(
                Files.inboundAdapter(this.input).autoCreateDirectory(true),
                { poller { it.fixedDelay(2000).maxMessagesPerPoll(1) } }
        ) {

//            handle("converter", "xmlToObject")
//            handle("db", "saveAll")

//            handle(Files.outboundAdapter(xml).autoCreateDirectory(true))


            filter<File> {
                it.isFile
            }
            route<File> {
                when (it.extension.toLowerCase()) {
                    "xml" -> channels.run()
                    else -> channels.errors()
                }
            }


        }

//        @Bean
//        fun addToDbFlow() = integrationFlow {
//            handle("converter", "xmlToObject")
//            handle("db", "saveAll")
//        }


        @Bean
        fun xmlFlow() = integrationFlow(channels.xml()) {
            handle(Files.outboundAdapter(output).deleteSourceFiles(true).autoCreateDirectory(true))

        }

        @Bean
        fun fileToDb() = integrationFlow(channels.run()) {
            handle("converter", "xmlToObject")
            handle("db", "saveAll")
            handle(Files.outboundAdapter(output).deleteSourceFiles(true).autoCreateDirectory(true))
        }

        @Bean
        fun errFlow() = integrationFlow(channels.errors()) {
            handle(Files.outboundAdapter(errors).autoCreateDirectory(true).deleteSourceFiles(true))
        }
    }


}