package nl.acme.carapp.kotlindemo.generics

import nl.acme.carapp.utils.Assert.Companion.assertEquals

// PECS ::= Producer extends(out), Consumer super (in)

fun main() {
    val v = VehicleConsumerImplementor()
    v.drive(34)

    val v2 = VehicleProducerConsumerImplementor()
    v2.drive(3.5)
//    v2.drive(4) // fails since the Int literal does not conform to the expected type: Double

    val everything: EverythingIsOk<*> = EverythingIsOk(3)
    assertEquals(3, everything.get())
}

// shows an interface with an in and an implementor, the Consumer
interface VehicleConsumer<in T: Number> {
    fun drive(delta: T): ULong
}
class VehicleConsumerImplementor(var mileage: Long = 0): VehicleConsumer<Long> {

    override fun drive(delta: Long): ULong {
        this.mileage += delta
        return this.mileage.toULong()
    }
}

// shows a class with an out and and implementor, a Producer
class EverythingIsOk<out T: Number>(val t: T) {
    fun get() = t
}


/// ====

interface VehicleProducerConsumer<T: Number> {
    fun drive(delta: T): T
}

// be clear that both is OK but the list only supports Doubles
class VehicleProducerConsumerImplementor: VehicleProducerConsumer<Double> {
    private val content = mutableListOf<Double>()

    override fun drive(delta: Double): Double {
        this.content.add(delta)
        return delta;
    }
}