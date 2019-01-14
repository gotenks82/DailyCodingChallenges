import java.util.*

/**
Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).

For example, given the string "([])[]({})", you should return true.

Given the string "([)]" or "((()", you should return false.
 */

fun main(args: Array<String>) {
    require("([])[]({})".isBalanced()) { "Expectations not met." }
    require(!"([)]".isBalanced()) { "Expectations not met." }
    require(!"((()".isBalanced()) { "Expectations not met." }
    println("All tests passed")
}

fun String.isBalanced(): Boolean {
    val stack = Stack<Char>()
    this.forEach {
        when(it) {
            in listOf('(','[','{') -> stack.push(it)
            ')' -> if (stack.peek() == '(') stack.pop() else return false
            ']' -> if (stack.peek() == '[') stack.pop() else return false
            '}' -> if (stack.peek() == '{') stack.pop() else return false
        }
    }
    return stack.isEmpty()
}