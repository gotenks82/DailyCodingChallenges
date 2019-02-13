/**
Implement an LRU (Least Recently Used) cache. It should be able to be initialized with a cache size n, and contain the following methods:

set(key, value): sets key to value. If there are already n items in the cache and we are adding a new item,
then it should also remove the least recently used item.
get(key): gets the value at key. If no such key exists, return null.
Each operation should run in O(1) time.
 */

fun main(args: Array<String>) {
    val cache = LRUCache(5)
    (0 until 10).forEach {
        cache.set(('a'+it).toString(),"$it")
        cache.printCache()
    }
    cache.get("h")
    cache.printCache()
    cache.get("f")
    cache.printCache()
    cache.set("final", "10")
    cache.printCache()
}

class LRUCache(val size: Int) {
    val cache = mutableMapOf<String, CacheNode>()
    val history = History()

    fun set(key: String, value: String) {
        if (cache.keys.size == size) {
            history.popLeastRecentlyUsed()?.let { cache.remove(it.key) }
        }

        cache[key]?.let { history.remove(it) }
        val node = CacheNode(key, value)
        cache[node.key] = node
        history.nodeAccessed(node)
    }

    fun get(key: String): String? {
        val node = cache[key]
        if (node != null) {
            history.nodeAccessed(node)
        }
        return node?.value
    }

    fun printCache() {
        println(history)
    }
}

data class CacheNode(val key: String, val value: String) {
    var next: CacheNode? = null
    var prev: CacheNode? = null

    fun bypass() {
        this.prev?.next = this.next
        this.next?.prev = this.prev
    }
}

class History {
    var mostRecentlyUsed: CacheNode? = null
    var leastRecentlyUsed: CacheNode? = null

    fun nodeAccessed(node: CacheNode) {
        remove(node)
        node.prev = null
        node.next = mostRecentlyUsed
        mostRecentlyUsed?.prev = node
        mostRecentlyUsed = node
        if (leastRecentlyUsed == null) leastRecentlyUsed = node
    }

    fun popLeastRecentlyUsed(): CacheNode? {
        val lru = leastRecentlyUsed
        lru?.bypass()
        leastRecentlyUsed = lru?.prev
        return lru
    }

    fun remove(node: CacheNode) {
        node.bypass()
        if (node == leastRecentlyUsed) {
            leastRecentlyUsed = node.prev
        }
        if (node == mostRecentlyUsed) {
            mostRecentlyUsed = node.next
        }
    }

    override fun toString(): String {
        var curr = mostRecentlyUsed
        val stringBuilder = StringBuilder()
        while (curr != null) {
            stringBuilder.append("$curr,")
            curr = curr.next
        }

        return stringBuilder.toString()
    }
}