/**
Given an array of numbers, find the length of the longest increasing subsequence in the array. The subsequence does not necessarily have to be contiguous.

For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15], the longest increasing subsequence has length 6: it is 0, 2, 6, 9, 11, 15.
 */

fun main(args: Array<String>) {
    println(findLengthOfIncreasingSubsequence(listOf(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)))
}

fun findLengthOfIncreasingSubsequence(list: List<Int>): Int {
    val subsequences = mutableListOf<List<Int>>()
    list.forEach { i ->
        if (subsequences.isEmpty()) {
            subsequences.add(listOf(i))
        } else {
            // find the subsequences that end with a number lower than the current, for each one add a new subsequence that includes the current value
            subsequences.filter { it.last() < i }.map { it + i }.takeIf { it.isNotEmpty() }?.let { subsequences.addAll(it) }
        }
    }
    return subsequences.map { it.size }.max() ?: 0
}