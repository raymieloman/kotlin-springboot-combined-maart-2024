package nl.acme.carapp.config

import nl.acme.carapp.model.Car
import nl.acme.carapp.model.CarOwner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyBeans {

    @Bean("mijnBwm")
    fun bmw(): Car {
        val bmw = Car()
        bmw.licensePlate = "bmw"
        bmw.mileage = 1000.0

        return bmw;
    }

    @Bean
    fun jan(): CarOwner {
        return CarOwner("jan")
    }
}