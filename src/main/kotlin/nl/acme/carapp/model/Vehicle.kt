package nl.acme.carapp.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, // (1)
    include = JsonTypeInfo.As.EXISTING_PROPERTY, // type field is provided by @JsonProperty on fun type()
    property = "type" //(2)
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Car::class, name = "car"),
    JsonSubTypes.Type(value = Truck::class, name = "truck")
)
@Entity
abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    open var id: Long? = null

    abstract fun type(): String
}