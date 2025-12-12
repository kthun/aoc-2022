fun main() {

    fun moveFollowingKnot(newKnotA: Pair<Int, Int>, knotB: Pair<Int, Int>): Pair<Int, Int> {
        var a = newKnotA
        var b = knotB

        var newTailX = b.first
        var newTailY = b.second
        if (a.first - b.first >= 2) {
            newTailX++
            if (a.second != b.second) {
                newTailY = a.second
            }
        } else if (a.first - b.first <= -2) {
            newTailX--
            if (a.second != b.second) {
                newTailY = a.second
            }
        } else if (a.second - b.second >= 2) {
            newTailY++
            if (a.first != b.first) {
                newTailX = a.first
            }
        } else if (a.second - b.second <= -2) {
            newTailY--
            if (a.first != b.first) {
                newTailX = a.first
            }
        }
        b = Pair(newTailX, newTailY)
        return b
    }

    fun part1(input: List<String>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()

        var head = Pair(0, 0)
        var tail = Pair(0, 0)
        visited.add(tail)

        for (line in input) {
            val (direction, length) = line.split(" ")
            val (dx, dy) = when (direction) {
                "U" -> Pair(0, -1)
                "D" -> Pair(0, 1)
                "R" -> Pair(1, 0)
                "L" -> Pair(-1, 0)
                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
            repeat (length.toInt()) {
                head = Pair(head.first + dx, head.second + dy)
                tail = moveFollowingKnot(head, tail)
                visited.add(tail)
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val tailVisited = mutableSetOf<Pair<Int, Int>>()

        val knots = List(10) { Pair(0, 0) }.toMutableList()
        var head = knots.first()
        var tail = knots.last()
        tailVisited.add(tail)

        for (line in input) {
            val (direction, length) = line.split(" ")
            val (dx, dy) = when (direction) {
                "U" -> Pair(0, -1)
                "D" -> Pair(0, 1)
                "R" -> Pair(1, 0)
                "L" -> Pair(-1, 0)
                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
            repeat (length.toInt()) {
                head = Pair(head.first + dx, head.second + dy)
                for (i in 1..knots.lastIndex) {
                    knots[i] = moveFollowingKnot(knots[i - 1], knots[i])
                }
                tailVisited.add(knots.last())
            }
        }
        return tailVisited.size
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    val input = readInput("Day09")

//    check(part1(testInput) == 13)
    println(part1(input))

    println(part2(testInput))

//    println(part1(input))
//    println(part2(input))
}
