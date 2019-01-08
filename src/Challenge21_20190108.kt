/**
Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.

For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 */

fun main(args: Array<String>) {
    require(findRoomCount(listOf(30 to 75, 0 to 50, 60 to 150)) == 2)
    require(findRoomCount(listOf(30 to 75, 0 to 50, 60 to 150, 0 to 20)) == 2)
    require(findRoomCount(listOf(30 to 75, 0 to 50, 60 to 150, 0 to 40)) == 3)
    require(findRoomCount(listOf(30 to 75, 0 to 50, 35 to 150, 0 to 40, 100 to 140, 200 to 300)) == 4)
    println("All tests passed")
}

fun findRoomCount(lectures: List<Pair<Int,Int>>): Int {
    val sortedLectures = lectures.sortedWith(Comparator{ a,b ->
        when {
            a.first == b.first -> a.second.compareTo(b.second)
            else -> a.first.compareTo(b.first)
        }
    })
    var roomCount = 0
    var indexStart = 0
    var indexEnd = 0
    var roomCountMax = 0

    while (indexStart < lectures.size) {
        if (sortedLectures[indexStart].first <= sortedLectures[indexEnd].second) {
            roomCount++
            indexStart++
        } else {
            roomCount--
            indexEnd++
        }
        if (roomCount > roomCountMax) roomCountMax = roomCount
    }

    return roomCountMax
}