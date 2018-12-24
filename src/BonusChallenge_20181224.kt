import java.time.Instant
import java.util.PriorityQueue

/**
 * return a new sorted merged list from K sorted lists, each with size N.
 * if we had [[10, 15, 30], [12, 15, 20], [17, 20, 32]], the result should be [10, 12, 15, 15, 17, 20, 20, 30, 32]
 */
fun main(args: Array<String>) {
    val lists = listOf(
        listOf(10, 15, 30, 32, 40, 55, 78, 100),
        listOf(12, 15, 20, 25, 38, 39, 60, 80, 99),
        emptyList(),
        listOf(17, 20, 32, 38, 40, 51)
    )

    time { println("Simple: ${simpleMethod(lists)}") }
    time { println("prioritySimple: ${priorityQueueSimpleMethod(lists)}")}
    time { println("priorityOptimized: ${limitedPriorityQueueMethod(lists)}")}
}

fun simpleMethod(lists: List<List<Int>>) = lists.flatten().sorted()

fun priorityQueueSimpleMethod(lists: List<List<Int>>): List<Int> {
    val priorityQueue = PriorityQueue<Int>(lists.size * lists.first().size)
    for (l in lists) {
        for (i in l) {
            priorityQueue.add(i)
        }
    }
    val resultList = ArrayList<Int>(priorityQueue.size)
    while (priorityQueue.size > 0) {
        resultList.add(priorityQueue.poll())
    }
    return resultList
}

fun limitedPriorityQueueMethod(lists: List<List<Int>>): List<Int> {
    val k = lists.size
    val currIndexes = Array(k) { 0 }
    val priorityQueue = PriorityQueue<ElemFromList>(lists.size)
    val resultList = ArrayList<Int>(k * lists.first().size)

    lists.forEachIndexed { listIndex, list ->
        if (currIndexes[listIndex] < list.size) {
            priorityQueue.add(ElemFromList(list[currIndexes[listIndex]], listIndex))
        }
    }

    while (priorityQueue.size > 0) {
        priorityQueue.poll().let {
            val sourceList = lists[it.listIndex]
            resultList.add(it.value)
            currIndexes[it.listIndex]++
            if (currIndexes[it.listIndex] < sourceList.size) {
                priorityQueue.add(ElemFromList(sourceList[currIndexes[it.listIndex]], it.listIndex))
            }
        }
    }
    return resultList
}

class ElemFromList(val value: Int, val listIndex: Int) : Comparable<ElemFromList> {
    override fun compareTo(other: ElemFromList) = this.value.compareTo(other.value)
}

fun time(fn: () -> Unit) {
    val start = Instant.now().toEpochMilli()
    fn()
    println("ExecutionTime: ${Instant.now().toEpochMilli() - start}")
}
