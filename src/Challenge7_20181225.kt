/**
Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

You can assume that the messages are decodable. For example, '001' is not allowed
 */

fun main(args: Array<String>) {
    val possibleDecodedMessages = possibleDecodedMessages("11121")
    println(possibleDecodedMessages.size)
    println(possibleDecodedMessages.joinToString("\n"))
}

val mappings = (1..26).map {
    "$it" to ('a' + (it - 1)).toString()
}.toMap()

val mappingsMaxLength = 2

fun possibleDecodedMessages(encodedMsg: String): List<String> {
    if (encodedMsg.isEmpty()) {
        return listOf("")
    }
    val list = mutableListOf<String>()

    (1..mappingsMaxLength).forEach { n ->
        encodedMsg.takeIf { it.length > n - 1 }?.take(n).let { token ->
            mappings[token]?.let { mappedToken ->
                list.addAll(possibleDecodedMessages(encodedMsg.drop(n)).map { "$mappedToken$it" })
            }
        }
    }
    return list
}