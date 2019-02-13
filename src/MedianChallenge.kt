/**
Compute the running median of a sequence of numbers. That is, given a stream of numbers, print out the median of the list so far on each new element.

Recall that the median of an even-numbered list is the average of the two middle numbers.

For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should print out:

2
1.5
2
3.5
2
2
2
 */

fun main(args: Array<String>) {
    val list = mutableListOf<Int>()

    listOf(2, 1, 5, 7, 2, 0, 5).forEach {
        list.add(it)
        println(calculateMedian(list))
    }
}

fun calculateMedian(list: MutableList<Int>) =
    list.let {
        it.sort()
        val mid = list.lastIndex / 2
        when (list.size % 2) {
            1 -> list[mid].toDouble()
            else -> (list[mid] + list[mid + 1]) / 2.0
        }
    }

