package nl.acme.kotlindemo.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.springframework.web.servlet.function.ServerResponse.async
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

fun main() {
      baseDemo() // toggle this on and off
//    demoContext()
//    demoRunBlocking() // toggle this on and off
//    demoJobAndWaiting()
//    demoJobAndCancellation()
//    demoJobAndCancellationAndKnowThatIAmCancelled()
//    demoJobAndCancellationAndKnowThatIAmCancelledImprovedWithCancellationException()
//    demoJobWithTimeout()
//    demoWithAsyncAwaitPrepareForCleanerCode()
//     demoWithAsyncAwait()

    // note to self: toggle this to show the result of the GlobalScope thread. Expect no result when no waiting here
    // this simulates a long running Android app
    Thread.sleep(7000) // rloman refactor this to a wait for the job finishing
    // note to self: see the broken arrow on the left.
}

fun baseDemo() {
    GlobalScope.launch {
        println("Coroutines says hello from thread ${Thread.currentThread().name}")
        println("Start waiting for network call in the suspend function, hence I will block now ... in this thread!")
        val data = networkCall1()
        println("Result from networkcall: $data")
    }
    println("Hello from thread ${Thread.currentThread().name}")
    println("In fact end of the program ... but waiting for the other coroutine to finish ...")
}

fun demoContext() {
    println("Starting in demoContext")
    // for android
    GlobalScope.launch(Dispatchers.IO) {
        val answer = networkCall1()
        println("Starting coroutine in thread: ${Thread.currentThread().name}")
        // we have data, switch back to main context

        withContext(newSingleThreadContext("MyShowDataThread")) {// IRL: Select Dispatchers.Main in the android dev env.
            println("Setting text in thread: ${Thread.currentThread().name}")
            // write to the android pane but here ....
            println(answer)
        }

    }
}

fun demoRunBlocking() {
//    delay(3) // error, no suspend or coroutine context here

    println("Before run blocking")
    runBlocking {// in fact makes from the async delay a sync delay
        println("Start of runBlocking, seconds: ${LocalDateTime.now().second}")
        delay(3) // OK
        // two launches which do NOT add up
        launch(Dispatchers.IO) {
            delay(3000L)
            println("Finished IO Coroutine 1, seconds: ${LocalDateTime.now().second}") // increment should be 3
        }
        launch(Dispatchers.IO) {
            delay(3000L)
            println("Finished IO Coroutine 2, seconds: ${LocalDateTime.now().second}") // increment should be 3
        }
        delay(5000)
        println("End of runBlocking, seconds: ${LocalDateTime.now().second}") // increment should be 2 (5-3 seconds delay above)
    }
    println("After runBlocking, seconds: ${LocalDateTime.now().second}")
}

fun demoJobAndWaiting() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        repeat(5) {
            println("Coroutine is still working ... ")
            delay(1000)
        }
    }

    // wait4job
    runBlocking {
        job.join()
        println("Main thread is continuing ... ")
    }
}

fun demoJobAndCancellation() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        repeat(5) {
            println("Coroutine is still working ... ")
            delay(1000)
        }
    }

    // wait4job
//    job.join() // fails, since join is a suspend function
    runBlocking {
        delay(2000)
        job.cancel()
        println("Main thread is continuing ... ")
    }
}

fun demoJobAndCancellationAndKnowThatIAmCancelled() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        println("Starting long running calculation ... ")
        for (i in 30..50) {
            if (isActive) { // without this if the job will continue since the loop is too fast and smart to check for a cancellation by default. You have to check it for yourself.
                println("Result fib for i = $i: ${fib(i)}")
            } else {
                break;
            }
        }
        println("Ending long running calculation ... ")
    }

    // wait4job
//    job.join() // fails, since join is a suspend function
    runBlocking {
        delay(50)
        job.cancel()
        println("job cancelled ... ")
    }
    println("end of program")
}

/*
!!! Be clear that the thrown CancellationException is build with the insight of that it is a silent exception
no stacktrace etc. is printed. Is is silently ignored (by design!)
 */
fun demoJobAndCancellationAndKnowThatIAmCancelledImprovedWithCancellationException() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        println("Starting long running calculation ... ")
        for (i in 30..50) {
            ensureActive() // ensureActive or throw CancellationException
            println("Result fib for i = $i: ${fib(i)}")
        }
        println("Ending long running calculation ... ")
    }

    // wait4job
//    job.join() // fails, since join is a suspend function
    runBlocking {
        delay(50)
        job.cancel()
        println("job cancelled ... ")
    }
}

fun demoJobWithTimeout() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        val timeout = 300L
        println("Starting long running calculation which should be cancelled automatically after ${timeout}ms... ")
        withTimeout(timeout) {
            for (i in 30..50) {
                if (isActive) { // without this if the job will continue since the loop is too fast and smart to check for a cancellation by default. You have to check it for yourself.
                    println("Result fib for i = $i: ${fib(i)}")
                } else {
                    break;
                }
            }
        }
        println("Ending long running calculation ... ")
    }
}

fun demoWithAsyncAwaitPrepareForCleanerCode() {
    GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {

            var answer1: String? = null;
            var answer2: String? = null;
            val job1 = launch {
                answer1 = networkCall1()
            }
            val job2 = launch {
                answer2 = networkCall2()
            }
            job1.join()
            job2.join()
            println("Answer1 is $answer1")
            println("Answer2 is $answer2")
        }
        println("Request took $time ms")

    }
}

// improved version of demoWithAsyncAwaitPrepare....
fun demoWithAsyncAwait() {
    GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {

            var answer1 = async {
                networkCall1()
            }

            var answer2 = async { networkCall2() }
            println("Answer1 is ${answer1.await()}")
            println("Answer2 is ${answer2.await()}")
        }
        println("Request took $time ms")
    }
}


fun fib(n: Int): Long {
    return if (n == 0 || n == 1) n.toLong()
    else fib(n - 2) + fib(n - 1)
}

suspend  fun networkCall1(): String {
    delay(5000)

    return "Answer 1"
}

suspend fun networkCall2(): String {
    delay(3000)

    return "Answer 2"
}

