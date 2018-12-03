package org.spacebison.adventofcode.aoc2018

fun main(args: Array<String>) {
    val input = getInputForDay(1)
    println(Day1.part1(input))
    println(Day1.part2(input))
}

object Day1 {

    fun part1(input: String) =
            getNumberSequence(input)
                    .sum()

    fun part2(input: String): Int {
        var partialSum = 0
        val visitedSums = HashSet<Int>()
        return getNumberSequence(input)
                .looping()
                .map { partialSum += it; partialSum }
                .first { !visitedSums.add(partialSum) }
    }

    private fun getNumberSequence(input: String): Sequence<Int> {
        return input.split(Regex("[\\s,]"))
                .asSequence()
                .filter { it.isNotEmpty() }
                .map { Integer.parseInt(it) }
    }

    private fun <T> Sequence<T>.looping() =
            generateSequence { this }.flatMap { it }
}