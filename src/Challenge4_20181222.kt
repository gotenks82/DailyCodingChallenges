/**
 * Good morning! Here's your coding interview problem for today.

Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.

For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

You can modify the input array in-place.

 */
fun main(args: Array<String>) {
    println(findLowestMissingPositive(arrayOf(1,2,0,7,8,-5,11,-9,3,4)))
}

fun findLowestMissingPositive(array: Array<Int>): Int {
    // "get rid" of negative ints and ints bigger than the array size and assign a positive value above the array size
    for (i in array.indices) {
        if (array[i] < 1 || array[i] > array.size) {
            array[i] = array.size + 1
        }
    }

    // for each `i` within the bounds of the array, set the value in array[i-1] to negative sign
    for (i in array.indices) {
        val temp = Math.abs(array[i])
        if (temp <= array.size && array[temp-1] > 0) {
            array[temp-1] = -array[temp-1]
        }
    }

    // find the first position in the array that is still positive, that index is the missing number
    for (i in array.indices) {
        if (array[i] > 0) {
            return i + 1
        }
    }

    return 0
}