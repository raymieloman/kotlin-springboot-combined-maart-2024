package nl.acme.carapp.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, // (1)
    include = JsonTypeInfo.As.EXISTING_PROPERTY, // type field is provided by @JsonProperty on fun type()
    property = "type" //(2)
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Car::class, name = "car"),
    JsonSubTypes.Type(value = Truck::class, name = "truck")
)
abstract class AbstractCar {
    abstract fun type(): String;
}