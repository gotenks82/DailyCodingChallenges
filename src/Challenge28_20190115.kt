/**
Write an algorithm to justify text. Given a sequence of words and an integer line length k, return a list of strings which represents each line, fully justified.

More specifically, you should have as many words as possible in each line. There should be at least one space between each word. Pad extra spaces when necessary so that each line has exactly length k. Spaces should be distributed as equally as possible, with the extra spaces, if any, distributed starting from the left.

If you can only fit one word on a line, then you should pad the right-hand side with spaces.

Each word is guaranteed not to be longer than k.

For example, given the list of words ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"] and k = 16, you should return the following:

["the  quick brown", # 1 extra space on the left
"fox  jumps  over", # 2 extra spaces distributed evenly
"the   lazy   dog"] # 4 extra spaces distributed evenly
 */

fun main(args: Array<String>) {
    justifyText(listOf("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"), 16).forEach {
        println("|$it| length: ${it.length}")
    }

    justifyText(listOf("the"), 16).forEach {
        println("|$it| length: ${it.length}")
    }
}

fun justifyText(words: List<String>, lineLength: Int): List<String> {
    val justifiedStrings = ArrayList<String>()
    var tempList = ArrayList<String>()
    var tempLength = 0

    for (word in words) {
        if (tempLength + word.length >= lineLength) {
            justifiedStrings.add(justifyLine(tempList, lineLength - tempLength + 1))
            tempList = ArrayList()
            tempLength = 0
        }
        tempList.add(word)
        tempLength += word.length + 1
    }
    if (tempLength > 0) {
        justifiedStrings.add(justifyLine(tempList, lineLength - tempLength + 1))
    }
    return justifiedStrings
}

fun justifyLine(words: List<String>, padding: Int): String {
    val count = (words.size - 1).coerceAtLeast(1)
    val avgSpc = padding / count
    val rest = padding % count

    return words.mapIndexed { index, w ->
        if (index < words.lastIndex || index == 0) {
            avgSpc.let {
                if (rest > index) {
                    it + 1
                } else it
            }.let {
                w.plus(" ".repeat(it))
            }
        } else w
    }.joinToString(" ")
}