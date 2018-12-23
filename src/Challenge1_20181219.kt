/**
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.

For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

Bonus: Can you do this in one pass?
 */

fun main(args: Array<String>) {
    println(findSumToK(arrayOf(10,15,3,7), 17))
}

fun findSumToK(array: Array<Int>, k: Int): Boolean {
    val leftovers = HashSet<Int>()

    for (i in array) {
        if (leftovers.contains(i)) return true
        leftovers.add(k-i)
    }
    return false
}