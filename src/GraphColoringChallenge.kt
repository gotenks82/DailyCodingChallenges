/**
Given an undirected graph represented as an adjacency matrix and an integer k,
write a function to determine whether each vertex in the graph can be colored
such that no two adjacent vertices share the same color using at most k colors.
 */

/**
 * An Adjacency matrix for an undirected graph is symmetrical
 */
val undirectedGraph = arrayOf(
    arrayOf(2,1,0,0,1,0),
    arrayOf(1,0,1,0,1,0),
    arrayOf(0,1,0,1,0,0),
    arrayOf(0,0,1,0,1,1),
    arrayOf(1,1,0,1,0,0),
    arrayOf(0,0,0,1,0,0)
)

/**
 * An undirected graph can always be colored with d+1 colors, where d is the maximum degree of the graph
 */
fun Array<Array<Int>>.canBeColoredWith(k: Int): Boolean {
    val graphMaxDegree = this.mapIndexed { rowIndex, row ->
        row.foldIndexed(0) { colIndex, acc, i ->
            if (colIndex != rowIndex) //ignore diagonal
                acc + i
            else
                acc
        }
    }.max() ?: -1

    return k > graphMaxDegree
}

fun main(args: Array<String>) {
    require(undirectedGraph.canBeColoredWith(6))
    require(undirectedGraph.canBeColoredWith(5))
    require(undirectedGraph.canBeColoredWith(4))
    require(!undirectedGraph.canBeColoredWith(3))
    require(!undirectedGraph.canBeColoredWith(2))
    require(!undirectedGraph.canBeColoredWith(1))
    println("all conditions passed!")
}
