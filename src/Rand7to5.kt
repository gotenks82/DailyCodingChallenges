import java.util.*

/**
Using a function rand7() that returns an integer from 1 to 7 (inclusive) with uniform probability, implement a function rand5() that returns an integer from 1 to 5 (inclusive).
 */

fun rand7() = Random().nextInt(7)

tailrec fun rand5(): Int = rand7().takeIf { it < 5 } ?: rand5()

fun main(args: Array<String>) {
    printDistribution("Rand7") { rand7() }
    printDistribution("Rand5") { rand5() }
}

const val repetitions = 1000000
fun printDistribution(title: String, randGen: () -> Int) {
    println("Distribution for $title")
    val results = mutableMapOf<Int, Int>()
    repeat(repetitions) {
        val result = randGen()
        results[result] = results.getOrDefault(result, 0) + 1
    }
    results.forEach { k,v ->
        println("$k -> ${(v.toDouble() / repetitions) * 100}")
    }
}