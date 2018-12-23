import java.lang.StringBuilder

/**
Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.

For example, given the following Node class

class Node:
def __init__(self, val, left=None, right=None):
self.val = val
self.left = left
self.right = right
The following test should pass:

node = Node('root', Node('left', Node('left.left')), Node('right'))
assert deserialize(serialize(node)).left.left.val == 'left.left'
 */

fun main(args: Array<String>) {
    val node = Node("root", Node("left", Node("left.left")), Node("right"))
    println(serialize(node))
    println(deserialize(serialize(node)).left?.left?.value == "left.left")
}

data class Node(val value: String, val left: Node? = null, val right: Node? = null)

fun serialize(node: Node): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("${node.value}|")
    var nodes = listOf(node.left, node.right)
    do {
        nodes.forEach { stringBuilder.append("${it?.value ?: ""}|") }
        nodes = nodes.map { listOf(it?.left, it?.right) }.flatten()
    } while (nodes.filterNotNull().isNotEmpty())

    return stringBuilder.toString()
}

fun deserialize(nodeString: String): Node {
    val tokens = nodeString.split("|")
    return buildNode(tokens, 1)
}

fun buildNode(tokens: List<String>, rootIndex: Int): Node {
    val rootValue = tokens[rootIndex - 1]
    if (rootIndex >= tokens.size/2) return Node(value = rootValue)

    val leftValue = tokens[(rootIndex * 2) -1]
    val rightValue = tokens[(rootIndex * 2)]
    return Node(
        value = rootValue,
        left = if (leftValue.isNotBlank()) buildNode(tokens, rootIndex * 2) else null,
        right = if (rightValue.isNotBlank()) buildNode(tokens, rootIndex * 2 + 1) else null
    )
}