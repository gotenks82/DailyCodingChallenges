import java.math.BigInteger

/**
There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.

For example, if N is 4, then there are 5 unique ways:

1, 1, 1, 1
2, 1, 1
1, 2, 1
1, 1, 2
2, 2
What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.

 */
fun main(args: Array<String>) {
    println(countUniqueWays(4))
    println(countUniqueWays(4, arrayOf(1)))
    println(countUniqueWays(4, arrayOf(1,3,5)))
    println(countUniqueWays(5, arrayOf(1,3,5)))
    println(countUniqueWaysConstantSpace(4))
    println(countUniqueWaysConstantSpace(4, arrayOf(1)))
    println(countUniqueWaysConstantSpace(4, arrayOf(1,3,5)))
    println(countUniqueWaysConstantSpace(5, arrayOf(1,3,5)))
    println(countUniqueWays(660, arrayOf(1,2,3,4,5,6)))
    println(countUniqueWaysConstantSpace(660, arrayOf(1,2,3,4,5,6)))
}

/**
 * With K being the number of possible "moves", and N the number of steps
 * This version of the solution has N * K = O(n) linear Time complexity, and O(n) linear space complexity
 * as we save all the intermediate results
 */
fun countUniqueWays(n: Int, possibleMoves: Array<Int> = arrayOf(1,2)): BigInteger {
    val cache = Array<BigInteger>(n+1) { BigInteger.ONE }

    for (i in 1..n) {
        cache[i] = possibleLastSteps(i, possibleMoves).fold(BigInteger.ZERO) { sum, step ->
            sum + cache[step]
        }
    }
    return cache.last()
}
fun possibleLastSteps(n: Int, possibleMoves: Array<Int>) = possibleMoves.map { n - it }.filter { it >= 0 }

/**
 * With K being the number of possible "moves", and M being the number of steps in the "longest move", and N the number of steps
 * This version of the solution has N * (K + M) = O(n) linear time complexity and min(M, N) space complexity.
 * This is because we only store the relevant calculations for the next step and shift the array at every iteration.
 * Assuming that N is the actual variable input and M is a predefined fixed value and N is generally bigger then M,
 * the space complexity is constant O(1).
 *
 * A linked list might have reduced the time complexity of the "shifting" operation by just dropping the head,
 * but it does not have constant indexed access
 *
 * Using collections.drop() or take() might have made for a more readable code but the implementation would have
 * increased both the time and the space estimations of the shifting operation as it uses intermediate lists
 */
fun countUniqueWaysConstantSpace(n: Int, possibleMoves: Array<Int> = arrayOf(1,2)): BigInteger {
    val max = requireNotNull(possibleMoves.max()).coerceAtMost(n + 1)
    val cache = Array<BigInteger>(max) { BigInteger.ZERO }
    cache[0] = BigInteger.ONE
    for (i in 1..n) {
        when {
            i < cache.size -> {
                cache[i] = possibleMoves.filter { it <= i }.fold(BigInteger.ZERO) { sum, step ->
                    sum + cache[i - step]
                }
            }
            else -> {
                val currentVal = possibleMoves.fold(BigInteger.ZERO) { sum, step ->
                    sum + cache[cache.size - step]
                }
                for (j in 1..cache.lastIndex) {
                    cache[j-1] = cache[j]
                }
                cache[cache.lastIndex] = currentVal
            }
        }
    }

    return cache.last()
}
