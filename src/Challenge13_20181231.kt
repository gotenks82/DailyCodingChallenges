/**
Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.

For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
 */

fun main(args: Array<String>) {
    require("abcba".findMaxLengthOfDistinct(2) == 3)
    require("abcba".findMaxLengthOfDistinct(3) == 5)
    require("abcba".findMaxLengthOfDistinct(1) == 1)
    require("abbcba".findMaxLengthOfDistinct(1) == 2)
    require("abbcba".findMaxLengthOfDistinct(2) == 4)
    require("abbcba".findMaxLengthOfDistinct(3) == 6)
}

/**
 * This implementation goes through the string 2 times, one to iterate over all characters,
 * one to move the left boundary of the current substring up, so overall time complexity is linear.
 * Space complexity depends on the value of k, so O(k), we could consider it O(1) if
 * we assume the input variable to be the string
 */
fun String.findMaxLengthOfDistinct(k: Int): Int = this.let { string ->
    var leftBoundaryExcl = -1
    val charCount = HashMap<Char, Int>()
    var distinctCount = 0
    var maxLength = 0

    string.forEachIndexed { index, c ->
        when {
            distinctCount < k || (distinctCount == k && charCount.containsKey(c)) -> {
                charCount[c] = (charCount[c] ?: 0) + 1
                if (charCount[c] == 1) {
                    distinctCount++
                }
                if (index - leftBoundaryExcl > maxLength) {
                    maxLength = index - leftBoundaryExcl
                }
            }
            distinctCount == k && !charCount.containsKey(c) -> {
                while (distinctCount == k) {
                    val firstChar = string[leftBoundaryExcl+1]
                    charCount[firstChar] = requireNotNull(charCount[firstChar]) - 1
                    if (charCount[firstChar] == 0) {
                        charCount.remove(firstChar)
                        distinctCount --
                    }
                    leftBoundaryExcl++
                }
                distinctCount ++
                charCount[c] = 1

                if (index - leftBoundaryExcl > maxLength) {
                    maxLength = index - leftBoundaryExcl
                }
            }
        }
    }

    return maxLength
}