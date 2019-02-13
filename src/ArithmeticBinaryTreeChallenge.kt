import java.math.BigDecimal

/**
Suppose an arithmetic expression is given as a binary tree. Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.

Given the root to such a tree, write a function to evaluate it.

For example, given the following tree:

 *
/ \
+    +
/ \  / \
3  2  4  5
You should return 45, as it is (3 + 2) * (4 + 5)
 */
const val MUL = "*"
const val DIV = "/"
const val ADD = "+"
const val SUB = "-"

val testTree = ArithmeticNode(
    MUL,
    ArithmeticNode(ADD, ArithmeticNode("3"), ArithmeticNode("2")),
    ArithmeticNode(ADD, ArithmeticNode("4"), ArithmeticNode("5"))
)

data class ArithmeticNode(val value: String, val left: ArithmeticNode? = null, val right: ArithmeticNode? = null)

// This assumes that the tree is a valid tree, where all node values are either operations or can be parsed to numbers
fun ArithmeticNode.result(): BigDecimal = when (value) {
    MUL -> requireNotNull(left).result() * requireNotNull(right).result()
    DIV -> requireNotNull(left).result() / requireNotNull(right).result()
    ADD -> requireNotNull(left).result() + requireNotNull(right).result()
    SUB -> requireNotNull(left).result() - requireNotNull(right).result()
    else -> value.toBigDecimalOrNull() ?: BigDecimal.ZERO
}

fun main(args: Array<String>) {
    require(testTree.result() == 45.toBigDecimal())
    println("All tests passed")
}