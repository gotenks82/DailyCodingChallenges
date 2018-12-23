/**
cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair.
For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.

Given this implementation of cons:

def cons(a, b):
def pair(f):
return f(a, b)
return pair
Implement car and cdr.
 */

fun main(args: Array<String>) {
    println("Car: "+car(cons(3,4)))
    println("Cdr: "+cdr(cons(3,4)))
}


fun cons(a: Int, b: Int): ((Int, Int) -> Int) -> Int {
    return fun(fn: (x:Int, y:Int) -> Int): Int { return fn(a,b) }
}

fun car(fn: ((Int, Int) -> Int) -> Int): Int {
    return fn { a,_ ->  a }
}

fun cdr(fn: ((Int, Int) -> Int) -> Int): Int {
    return fn { _,b ->  b }
}