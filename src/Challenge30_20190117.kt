/**
You are given an array of non-negative integers that represents a two-dimensional elevation map where each element is unit-width wall and the integer is the height.
Suppose it will rain and all spots between two walls get filled up.

Compute how many units of water remain trapped on the map in O(N) time and O(1) space.

For example, given the input [2, 1, 2], we can hold 1 unit of water in the middle.

Given the input [3, 0, 1, 3, 0, 5], we can hold
3 units in the first index,
2 in the second, and
3 in the fourth index (we cannot hold 5 since it would run off to the left),
so we can trap 8 units of water.
    #
#   #
# # # # #
####### # #
 */

fun main(args: Array<String>) {
    require(calculateUnitsOfWater(listOf(3,0,1,3,0,5)) == 8) { "Expectations not met." }
    require(calculateUnitsOfWater(listOf(2,1,2)) == 1) { "Expectations not met." }
    require(calculateUnitsOfWater(listOf(3,0,1,6,0,5)) == 10) { "Expectations not met." }
    println("All tests passed.")
}

/**
 * optimal algorithm in one pass found online, this is the kotlin implementation
 */
fun calculateUnitsOfWater(list: List<Int>): Int {
    var leftHeight = list.first()
    var waterPotential =0
    var unitsOfWater = 0

    for (i in 1..list.lastIndex) {
        val currHeight = list[i]
        if (currHeight > list[i-1]) {
            val capturedWater = (currHeight - list[i-1]).coerceAtMost(waterPotential)
            waterPotential -= capturedWater
            unitsOfWater += capturedWater
        }
        if (currHeight < leftHeight) {
            waterPotential += leftHeight - currHeight
        } else {
            unitsOfWater += waterPotential
            waterPotential = 0
            leftHeight = currHeight
        }
    }

    return unitsOfWater
}
