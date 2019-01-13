import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader

/**
Given a singly linked list and an integer k, remove the kth last element from the list.
k is guaranteed to be smaller than the length of the list.

The list is very long, so making more than one pass is prohibitively expensive.

Do this in constant space and in one pass.
 */

fun main(args: Array<String>) {
    val linkedList = listOf(1,2,3,4,5,6,7,8,9).toSinglyLinkedList()
    println(linkedList.string())
    removeKthLast(linkedList, 3)
    println(linkedList.string())
}

fun removeKthLast(root: SinglyLinkedNode, k: Int) {
    var current: SinglyLinkedNode? = root
    var index = -1
    var kthPlusOneLastNode: SinglyLinkedNode? = root
    while (current != null) {
        if (index > k) {
            kthPlusOneLastNode = kthPlusOneLastNode?.next
        }
        current = current.next
        index ++
    }

    kthPlusOneLastNode?.next?.let {
        it.next = it.next?.next
    }
}

data class SinglyLinkedNode(val value: Int) {
    var next: SinglyLinkedNode? = null
}

fun SinglyLinkedNode.string(): String = "$value ${next?.string() ?: ""}"

fun List<Int>.toSinglyLinkedList(): SinglyLinkedNode {
    val root = SinglyLinkedNode(this.first())
    var current = root
    for (i in 1..this.lastIndex) {
        val newNode = SinglyLinkedNode(this[i])
        current.next = newNode
        current = newNode
    }
    return root
}