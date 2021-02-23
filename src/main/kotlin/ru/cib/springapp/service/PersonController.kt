package ru.cib.springapp.service

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service



@Service
class PersonController {

    @Bean
    fun savePersonToDBFromXML() {
        PersonServiceImpl().saveAll(SpringJAXBConverter().xmlToObject("src/main/resources/File/file.xml"))
    }

}