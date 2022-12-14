fun main() {

    fun Char.characterValue(): Int = when {
        isLowerCase() -> code - 'a'.code + 1
        else -> code - 'A'.code + 27
    }

    fun part1(input: List<String>): Int = input.sumOf { line ->
        val midPoint = line.length / 2
        val compartmentA = line.substring(0, midPoint)
        val compartmentB = line.substring(midPoint)
        val match = compartmentA.find { it in compartmentB }!!
        match.characterValue()
    }

    fun part2(input: List<String>): Int =
        input.chunked(3).sumOf { lines ->
            val match = lines[0].find { it in lines[1] && it in lines[2] }!!
            match.characterValue()
        }

    val testInput = readInput("Day03_test")

    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
