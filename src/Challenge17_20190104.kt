/**
Suppose we represent our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
subdir1
subdir2
file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
subdir1
file1.ext
subsubdir1
subdir2
subsubdir2
file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.

Note:

The name of a file contains at least a period and an extension.

The name of a directory or sub-directory will not contain a period.
 */

fun main(args: Array<String>) {
    println(findLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"))
}

/**
 * Creates a directory tree while parsing the string
 * Splits by newline char, calculates directory level by counting tab chars,
 * when the next element is of a lower level, navigates the tree to the proper parent
 * Calculates the length of the longest path during the process.
 */
fun findLongestPath(filesystem: String): Int {
    val nodes = filesystem.split("\n")
    val root = FsNode(nodes.first(), 0, nodes.first())
    var longestPath = 0
    var currentNode = root
    nodes.drop(1).forEach {
        var elemLevel = 0
        var elem = it
        while (elem.startsWith("\t")) {
            elemLevel++
            elem = elem.drop(1)
        }
        while (elemLevel > 0 && elemLevel <= currentNode.level) {
            currentNode = requireNotNull(currentNode.parent)
        }
        val newNode = FsNode(elem, elemLevel,"${currentNode.absolutePath}/$elem" , currentNode)
        currentNode.addChild(newNode)
        currentNode = newNode
        if (newNode.isFile() && newNode.absolutePath.length > longestPath) {
            longestPath = newNode.absolutePath.length
        }
    }

    return longestPath
}

data class FsNode(
        val name: String,
        val level: Int,
        val absolutePath: String,
        val parent: FsNode? = null
) {
    var children = ArrayList<FsNode>()
    fun addChild(node: FsNode) {
        children.add(node)
    }
    fun isFile() = name.contains(".")
}
