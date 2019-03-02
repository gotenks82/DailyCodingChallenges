/**
Given a list of integers, return the largest product that can be made by multiplying any three integers.

For example, if the list is [-10, -10, 5, 2], we should return 500, since that's -10 * -10 * 5.
 */

fun main(args: Array<String>) {
    println(largestProductOfNitems(listOf(-10,-10,5,2), 3))
    println(largestProductOfNitems(listOf(-10,-10,5,2,3,-15), 3))
}

/**
 * This function keeps track of the N indexes that compose the largest product.
 * First sets the n indexes to the first n positions in the list, then scans the remaining items,
 * and for each item verifies if it can replace any index to achieve a higher product
 * This way, given M the size of the list, and N the number of items to multiply, we have a space complexity of O(N)
 * and a time complexity of O(N*M),
 */
fun largestProductOfNitems(list: List<Int>, n: Int): Int {
    val indexes = IntArray(n.coerceAtMost(list.size)) { i -> i }
    var maxProduct = indexes.fold(1) { acc, i -> acc * list[i] }

    for (k in n until list.size ) {
        var newMax = 0
        var replacedIndex = -1
        indexes.forEachIndexed { i, listIndex ->
            val product = (maxProduct / list[listIndex]) * list[k]
            if (product > newMax) {
                newMax = product
                replacedIndex = i
            }
        }
        if (newMax > maxProduct) {
            maxProduct = newMax
            indexes[replacedIndex] = k
        }
    }
    return maxProduct
}
