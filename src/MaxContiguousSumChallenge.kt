/**
Given an array of numbers, find the maximum sum of any contiguous subarray of the array.

For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137, since we would take elements 42, 14, -5, and 86.

Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.

Do this in O(N) time.
 */

fun main(args: Array<String>) {
    require(findMaxContiguousSum(arrayOf(34, -50, 42, 14, -5, 86)) == 137)
    require(findMaxContiguousSum(arrayOf(34, -50)) == 34)
    require(findMaxContiguousSum(arrayOf(34, -50, 42)) == 42)
    require(findMaxContiguousSum(arrayOf(42, -50, 34)) == 42)
    require(findMaxContiguousSum(arrayOf(-5, -1, -8, -9)) == 0)
    println("All tests passed")
}

fun findMaxContiguousSum(array: Array<Int>): Int {
    var maxSum = 0
    var tempSum = 0
    array.forEach {
        if (tempSum + it > 0) {
            tempSum += it
        } else {
            tempSum = 0
        }

        if (tempSum > maxSum) maxSum = tempSum
    }

    return maxSum
}