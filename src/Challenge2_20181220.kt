import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.roundToInt

/**
Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.

For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].

Follow-up: what if you can't use division?
 */

fun main(args: Array<String>) {
    println(multiplyOthers(arrayOf(1,2,3,4,5)).joinToString())
    println(multiplyOthersNoDiv(arrayOf(1,2,3,4,5)).joinToString())
}

fun multiplyOthers(array: Array<Int>): Array<Int> {
    val totalProduct = array.fold(1) { pr, n -> pr * n }
    return array.map { i -> totalProduct / i }.toTypedArray()
}

fun multiplyOthersNoDiv(array: Array<Int>): Array<Int> {
    val logArray = array.map { i -> ln(i.toDouble()) }
    val totalSum = logArray.fold(0.toDouble()) { sum, n -> sum + n}
    return logArray.map { n -> exp(totalSum - n).roundToInt()}.toTypedArray()
}