package nl.acme.carapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

data class Employee(val name: String)

@Configuration
class EmployeeBeans {

    @Bean
    fun johnEmployee(): Employee {

        return Employee("John")
    }

    @Primary
    @Bean
    fun tonyEmployee(): Employee {
        return Employee("Tony")
    }
}