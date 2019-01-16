/**
Run-length encoding is a fast and simple method of encoding strings.
The basic idea is to represent repeated successive characters as a single count and character.
For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".

Implement run-length encoding and decoding.
You can assume the string to be encoded have no digits and consists solely of alphabetic characters.
You can assume the string to be decoded is valid.
 */

fun main(args: Array<String>) {
    "AAAABBBCCDAA".encodeRunLength().run {
        println(this)
        println(this.decodeRunLength())
    }
}

fun String.encodeRunLength(): String {
    var currChar = this[0]
    var currCount = 1
    val builder = StringBuilder()
    for (i in 1..this.lastIndex) {
        if (currChar == this[i]) {
            currCount++
        } else {
            builder.append(currCount).append(currChar)
            currChar = this[i]
            currCount = 1
        }
    }
    builder.append(currCount).append(currChar)
    return builder.toString()
}

fun String.decodeRunLength(): String {
    val builder = StringBuilder()
    for (i in 0..this.lastIndex / 2) {
        val index = i * 2
        builder.append("${this[index + 1]}".repeat(this[index] - '0'))
    }
    return builder.toString()
}