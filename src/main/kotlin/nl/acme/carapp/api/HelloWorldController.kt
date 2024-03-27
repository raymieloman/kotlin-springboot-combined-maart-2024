package nl.acme.carapp.api

import nl.acme.carapp.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {


    @GetMapping
    fun hello(): String  {
        return "hello world!"
    }
}

// Java !
/*
class Service {

    @Autowired // field injection (minder gebruikelijk)
    private MyComponent component;

    @Autowired // constructor injection (beter)
    public Service(Mycomponent component) {
        this.component = component;
    }

    @Autowired
    public void setComponent(MyComponent component) {
        this.component = component;
    }
}
 */