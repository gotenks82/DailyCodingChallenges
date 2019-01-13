/**
Implement regular expression matching with the following special characters:

. (period) which matches any single character
 * (asterisk) which matches zero or more of the preceding element
That is, implement a function that takes in a string and a valid regular expression and returns whether or not
the string matches the regular expression.

For example, given the regular expression "ra." and the string "ray", your function should return true.
The same regular expression on the string "raymond" should return false.

Given the regular expression ".*at" and the string "chat", your function should return true.
The same regular expression on the string "chats" should return false.
 */

fun main(args: Array<String>) {
    require(match("ray", "ra.") == true, { "failed expectation" })
    require(match("raymond", "ra.") == false, { "failed expectation" })
    require(match("chat", ".*at") == true, { "failed expectation" })
    require(match("chats", ".*at") == false, { "failed expectation" })
    require(match("chatatatat", ".*at") == true, { "failed expectation" })
    println("All tests successful")
}

fun match(string: String, regexp: String): Boolean {
    var sIndex = 0
    var rIndex = 0
    while (sIndex <= string.lastIndex && rIndex >= 0) {
        if (rIndex > regexp.lastIndex) {
            rIndex = regexp.lastIndex
            while (rIndex >= 0 && regexp[rIndex] != '*') {
                rIndex--
            }
            sIndex--
        } else {
            when (regexp[rIndex]) {
                '.' -> {
                    sIndex++
                    rIndex++
                }
                '*' -> {
                    if (sIndex < string.lastIndex && rIndex < regexp.lastIndex && string[sIndex + 1] == regexp[rIndex + 1]) {
                        sIndex++
                        rIndex++
                    } else {
                        when (regexp[rIndex - 1]) {
                            '.' -> sIndex++
                            else -> {
                                if (string[sIndex] == regexp[rIndex - 1])
                                    sIndex++
                                else {
                                    rIndex++
                                }
                            }
                        }
                    }
                }
                else -> {
                    if (string[sIndex] == regexp[rIndex]) {
                        sIndex++
                        rIndex++
                    } else {
                        while (rIndex >= 0 && regexp[rIndex] != '*') {
                            rIndex--
                        }
                        sIndex--
                    }
                }
            }
        }
    }
    return sIndex == string.length && rIndex == regexp.length
}