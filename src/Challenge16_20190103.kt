/**
You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:

record(order_id): adds the order_id to the log
get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
You should be as efficient with time and space as possible.
 */

fun main(args: Array<String>) {
    val log = OrderLog(100)

    listOf((1..10),(100..250)).forEach { range ->
        range.forEach { log.record(it) }

        println(log)

        listOf(1, 2, 3, 4, 5, 6, 7, 8, 15, 95).forEach {
            println("value at last ${it}th position: ${log.getLast(it)}")
        }
    }
}

/**
 * This structure uses an array by filling it in a circular pattern.
 * currIndex keeps track of where to store the next orderId and is reset to 0 when it goes over the array boundary
 * Insertions time complexity is O(1) since we simply put the value at the currIndex position.
 * Time complexity for fetching the ith position is O(1) because we calculate the right index from the currIndex, and simply fetch that position from the array
 * Space complexity is also O(1) since it only depends on the fixed size of the log, not on the amount of orders recorded.
 */
class OrderLog(val size: Int) {
    private val log = Array<Int?>(size) { null }
    private var currIndex = 0
    private var currSize = 0

    fun record(orderId: Int) {
        log[currIndex] = orderId
        currIndex = (currIndex + 1) % size
        currSize = (currSize+1).coerceAtMost(size)
    }

    fun getLast(i: Int): Int? {
        if (i > currSize) return null

        return log[(size + currIndex - i) % size]
    }

    override fun toString(): String = StringBuilder().apply {
        append("Current Log:\n")
        for (i in log) {
            append("$i\n")
        }
    }.toString()
}