/**
In a directed graph, each node is assigned an uppercase letter. We define a path's value as the number of most frequently-occurring letter along that path. For example, if a path in the graph goes through "ABACA", the value of the path is 3, since there are 3 occurrences of 'A' on the path.

Given a graph with n nodes and m directed edges, return the largest value path of the graph. If the largest value is infinite, then return null.

The graph is represented with a string and an edge list. The i-th character represents the uppercase letter of the i-th node. Each tuple in the edge list (i, j) means there is a directed edge from the i-th node to the j-th node. Self-edges are possible, as well as multi-edges.

For example, the following input graph:

ABACA
[(0, 1),
(0, 2),
(2, 3),
(3, 4)]
Would have maximum value 3 using the path of vertices [0, 2, 3, 4], (A, A, C, A).

The following input graph:

A
[(0, 0)]
Should return null, since we have an infinite loop.
 */
data class Graph(
        val nodes: String,
        val vertices: List<Pair<Int, Int>>
) {
    fun getNode(vertex: Int) = nodes[vertex]
    fun getStart() = vertices.first().first
    fun getVerticesFrom(vertex: Int) = vertices.filter { it.first == vertex }.map { it.second }
}

data class Path(
        val graph: Graph,
        val occurrenceMap: Map<Char, Int>,
        val pathVertices: List<Int>,
        val hasLoop: Boolean = false
) {
    val value: Int
        get() {
            return occurrenceMap.values.max() ?: 0
        }

    fun nextVertex(vertex: Int) = this.copy(
            occurrenceMap = this.occurrenceMap.toMutableMap().also {
                it[graph.getNode(vertex)] = this.occurrenceMap.getOrDefault(graph.getNode(vertex), 0) + 1
            },
            pathVertices = this.pathVertices + vertex,
            hasLoop = this.pathVertices.contains(vertex)
    )
}

fun findLargestValuePath(graph: Graph): Path? {
    val paths = getPaths(graph, Path(graph, emptyMap(), emptyList()), HashSet())
    return paths.takeIf { it.all { path -> !path.hasLoop } }?.maxBy { it.value }
}

fun getPaths(graph: Graph, path: Path, visited: HashSet<Int>): List<Path> {
    val (currentNode, nextVertices) = if (path.pathVertices.isEmpty()) {
        -1 to listOf(graph.getStart())
    } else {
        path.pathVertices.last() to graph.getVerticesFrom(path.pathVertices.last())
    }

    return listOf(path) + if (visited.contains(currentNode) || path.hasLoop) {
        emptyList()
    } else {
        visited.add(currentNode)
        nextVertices.map { getPaths(graph, path.nextVertex(it), visited) }.flatten()
    }
}

fun main(args: Array<String>) {
    val graph = Graph("ABACA", listOf(
            0 to 1,
            0 to 2,
            2 to 3,
            3 to 4
    ))
    val largestValuePath = findLargestValuePath(graph)

    println(largestValuePath)

    println(findLargestValuePath(Graph("A", listOf(0 to 0))))
}
