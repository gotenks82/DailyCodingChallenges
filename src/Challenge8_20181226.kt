/**
A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:

        0
      /  \
    1     0
        /  \
       1    0
      /  \
     1    1

 */

fun main(args: Array<String>) {
    println(
        countUnivalTrees(
            BinaryNode(
                0,
                BinaryNode(1), BinaryNode(
                    0,
                    BinaryNode(
                        1,
                        BinaryNode(1), BinaryNode(1)
                    ), BinaryNode(0)
                )
            )
        )
    )
}

data class BinaryNode(val value: Int, val left: BinaryNode? = null, val right: BinaryNode? = null)

fun countUnivalTrees(root: BinaryNode): Int {
    if (root.left == null && root.right == null) return 1

    val leftCount = root.left?.let { countUnivalTrees(it) } ?: 0
    val rightCount = root.right?.let { countUnivalTrees(it) } ?: 0

    return if (leftCount > 0 && rightCount > 0 && root.value == root.left?.value && root.value == root.right?.value) {
        1
    } else {
        0
    } + leftCount + rightCount
}
