import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.time.Instant

/**
Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.

 This implementation requires to include the library: org.jetbrains.kotlinx:kotlinx-coroutines-core:0.27.0
 to the project
 */

fun main(args: Array<String>) {
    runBlocking {
        val scheduledJob = schedule({
            println(Instant.now())
            println("This is the scheduled execution")
        }, 5000)

        println(Instant.now())
        println("The call was scheduled")
        scheduledJob.await()
    }
}

fun schedule(f: () -> Unit, n: Long) =
    GlobalScope.async {
        delay(n)
        f()
    }
