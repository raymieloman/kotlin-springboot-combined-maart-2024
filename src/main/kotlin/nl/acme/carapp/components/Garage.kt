package nl.acme.carapp.components

import jakarta.annotation.PostConstruct
import nl.acme.carapp.config.Employee
import nl.acme.carapp.utils.Assert
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/*
We fix the ambiguous bean issue by choosing between two options
1. Using the qualifier with the name (which is the name of the method or the bean name)
2. Using the @Primary annotation when defining the bean

Adddional:
to show that the beanName can be different than the method name, see bean with name johnEmployee in EmployeeBeans and see the @Qualifier hier!
 */

@Component
class Garage(@Qualifier("johnEmployee") private val johnEmployee: Employee) {

    @PostConstruct
    fun validate() {
        Assert.assertEquals("John", this.johnEmployee.name)
    }
}