/**
Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.

For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].

Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.
 */

fun main(args: Array<String>) {
    println(simpleAutocomplete("de"))
    println(simpleAutocomplete("unknown"))

    println(preprocessedAutocomplete("de"))
    println(preprocessedAutocomplete("unknown"))
}

fun simpleAutocomplete(s: String) = dictionary.filter { it.startsWith(s) }

fun preprocessedAutocomplete(s: String): List<String> = preprocessedDictionary[s] ?: emptyList()

val dictionary = listOf(
    "dog", "deer", "deal"
)

val preprocessedDictionary = dictionary.fold(HashMap<String, ArrayList<String>>()) { map, item ->
    for (i in 1 until item.length) {
        val key = item.take(i)
        if (!map.containsKey(key)) {
            map[key] = ArrayList()
        }
        map[key]?.add(item)
    }
    map
}