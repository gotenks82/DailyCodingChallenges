/**
Given a dictionary of words and a string made up of those words (no spaces), return the original sentence in a list.
If there is more than one possible reconstruction, return any of them.
If there is no possible reconstruction, then return null.

For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox",
you should return ['the', 'quick', 'brown', 'fox'].

Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond",
return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].
 */

fun main(args: Array<String>) {
    println(getSentenceInAList("thequickbrownfox", listOf("the", "quick", "brown", "fox")))
    println(getSentenceInAList("bedbathandbeyond", listOf("bed", "bath", "bedbath", "and", "beyond")))
}

fun getSentenceInAList(sentence: String, words: List<String>): List<String> {
    var cur = 0
    var lastWordIndex = 0
    var currentWordIndex = 0
    var currentWord = words[currentWordIndex]
    val wordList = ArrayList<String>()
    while (cur <= sentence.length) {
        when {
            cur - lastWordIndex >= currentWord.length -> {
                wordList.add(currentWord)
                lastWordIndex = cur
                currentWordIndex = (currentWordIndex + 1) % words.size
                currentWord = words[currentWordIndex]
            }
            cur < sentence.length && sentence[cur] != currentWord[cur-lastWordIndex] -> {
                cur = lastWordIndex
                currentWordIndex = (currentWordIndex + 1) % words.size
                currentWord = words[currentWordIndex]
            }
            else -> {
                cur++
            }
        }
    }

    return wordList
}