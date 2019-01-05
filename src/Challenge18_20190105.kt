import java.util.*

/**
Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.

For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:

10 = max(10, 5, 2)
7 = max(5, 2, 7)
8 = max(2, 7, 8)
8 = max(7, 8, 7)
Do this in O(n) time and O(k) space. You can modify the input array in-place and you do not need to store the results. You can simply print them out as you compute them.
 */

fun main(args: Array<String>) {
    printMaxValsOfSubarrays(arrayOf(10,5,2,7,8,7), 3)
}

/**
 * Uses a rolling maxHeap of size K (space complexity O(k)) and goes through the array only once.
 * Time complexity for each insert in the maxHeap is log(K) but it can be considered constant, and
 * even if accessing N-k elements twice the overall time complexity is still O(N)
 */
fun printMaxValsOfSubarrays(array: Array<Int>, k: Int) {
    val maxHeap = PriorityQueue<Int>(k) { a,b -> b.compareTo(a) }

    array.forEachIndexed { index, i ->
        if (index < k) {
            maxHeap.add(i)
        } else  {
            maxHeap.remove(array[index-k])
            maxHeap.add(i)
        }

        if (index >= k-1) {
            println(maxHeap.peek())
        }
    }
}
