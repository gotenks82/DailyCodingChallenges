import java.util.Random
import kotlin.math.roundToInt

/**
The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.

Hint: The basic equation of a circle is x2 + y2 = r2
 */

fun main(args: Array<String>) {
    println(findPi())
}

/**
 * area of square of side r is r^2,
 * area of section of circle inside the square is (πr^2)/4.
 * Using r = 1, the area of the circle section is π/4, so area of circle / area of square = π/4
 * pi = 4 * (area of circle section/area of square)
 */
fun findPi(): Double {
    var stableFor = 0
    var lastResult = 0.0

    var pointsInside = 0.0
    var points = 0.0

    val xRandom = Random()
    val yRandom = Random()

    while(stableFor < 10000) {
        points ++

        val x = xRandom.nextDouble()
        val y = yRandom.nextDouble()

        val hypot = Math.hypot(x, y)
        if (hypot < 1) pointsInside ++

        val result = 4 * pointsInside/points
        Math.floor(result * 1000)

        if (Math.floor(result * 1000).roundToInt() == Math.floor(lastResult * 1000).roundToInt()) {
            stableFor++
        } else {
            stableFor = 0
        }
        println("curr: $result, stableFor: $stableFor, iterations: $points")
        lastResult = result
    }
    return lastResult
}