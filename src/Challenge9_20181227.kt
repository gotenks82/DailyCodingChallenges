import kotlin.math.max

/**
Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.

Follow-up: Can you do this in O(N) time and constant space?
 */

fun main(args: Array<String>) {
    println(getLargestNonAdjacentSum(arrayOf(5, 5, 10, 100, 10, 5)))
    println(optimalSumNonAdjacent(arrayOf(5, 5, 10, 100, 10, 5)))
}

fun getLargestNonAdjacentSum(array: Array<Int>): Int? {
    val sums = ArrayList<Int>()
    sumNextNonAdjacent(array, 0, 0, sums)
    sumNextNonAdjacent(array, 0, 1, sums)
    return sums.max()
}

fun sumNextNonAdjacent(array: Array<Int>, currSum: Int, currIndex: Int, sums: ArrayList<Int>) {
    if (currIndex >= array.lastIndex-1) {
        sums.add(currSum + array[currIndex])
    } else {
        (2..3).toList().filter { currIndex + it <= array.lastIndex }.forEach {
            sumNextNonAdjacent(array, currSum + array[currIndex], currIndex + it, sums)
        }
    }
}

// taken from GeeksForGeeks
fun optimalSumNonAdjacent(array: Array<Int>): Int {
    var maxIncludingPrevious = 0
    var maxExcludingPrevious = 0

    array.forEach { x ->
        val sumWithCurrent = maxExcludingPrevious + x
        maxExcludingPrevious = max(maxExcludingPrevious, maxIncludingPrevious)
        maxIncludingPrevious = sumWithCurrent
    }

    return max(maxIncludingPrevious, maxExcludingPrevious)
}