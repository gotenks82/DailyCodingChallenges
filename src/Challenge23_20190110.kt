/**
You are given an M by N matrix consisting of booleans that represents a board.
Each True boolean represents a wall. Each False boolean represents a tile you can walk on.

Given this matrix, a start coordinate, and an end coordinate,
return the minimum number of steps required to reach the end coordinate from the start.
If there is no possible path, then return null.
You can move up, left, down, and right. You cannot move through walls. You cannot wrap around the edges of the board.

For example, given the following board:

[[f, f, f, f],
[t, t, f, t],
[f, f, f, f],
[f, f, f, f]]
and start = (3, 0) (bottom left) and end = (0, 0) (top left),
the minimum number of steps required to reach the end is 7,
since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
 */
typealias Board = Array<Array<Boolean>>

typealias Position = Pair<Int, Int>

fun main(args: Array<String>) {
    println(findShortestPathLength(sampleBoard, Position(3, 0), Position(0, 0)))
}

/**
 * From the starting point, steps in all possible/allowed directions:
 * * if the End is included in the resulting steps, returns the stepcount
 * * avoids backtracking by carrying the previous set of cells and disallowing movement in that directions
 * * only allows steps within the border of the board
 * * if there are no possible steps left and END is not found, returns null
 */
fun findShortestPathLength(board: Board, start: Position, end: Position): Int? {
    return findShortestPathLength(board, arrayOf(start), emptyArray(), end, 0)
}

fun findShortestPathLength(board: Board, startCells: Array<Position>, previous: Array<Position>, end: Position, stepCount: Int): Int? {
    if (startCells.contains(end))
        return stepCount

    val nextCells = mutableSetOf<Position>()
    startCells.forEach { startCell ->
        startCell.getNeighbors().filter {
            !startCells.contains(it) && !previous.contains(it) &&
                    board.includes(it) && !board.isWall(it)
        }.forEach {
            nextCells.add(it)
        }
    }

    return if (nextCells.size > 0) findShortestPathLength(board, nextCells.toTypedArray(), startCells, end, stepCount + 1) else null
}

fun Position.getNeighbors(): List<Position> = listOf(
        this.first - 1 to this.second,
        this.first to this.second - 1,
        this.first + 1 to this.second,
        this.first to this.second + 1
).filter { it.first >= 0 && it.second >= 0 }

fun Board.includes(pos: Position): Boolean = this.size > pos.first && this[pos.first].size > pos.second
fun Board.isWall(pos: Position): Boolean = this.get(pos)
fun Board.get(pos: Position): Boolean = this[pos.first][pos.second]

val sampleBoard = arrayOf(
        arrayOf(false, false, false, false),
        arrayOf(true, true, false, true),
        arrayOf(false, false, false, false),
        arrayOf(false, false, false, false)
)