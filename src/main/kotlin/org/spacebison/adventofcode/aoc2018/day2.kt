package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.getInputForDay
import org.spacebison.adventofcode.aoc2018.common.pairCombinations

fun main(args: Array<String>) {
    val input = getInputForDay(2)
    println(Day2.part1(input))
    println(Day2.part2(input))
}

object Day2 {

    fun part1(input: String) =
            input.lineSequence()
                    .map { it.toCharArray().groupBy { it }.map { it.value.size }.toSet() }
                    .run {
                        count { it.contains(2) } * count { it.contains(3) }
                    }

    fun part2(input: String) =
            input.lineSequence()
                    .map { it.toCharArray() }
                    .pairCombinations()
                    .asSequence()
                    .map { it.first.zip(it.second) }
                    .first { it.count { it.first != it.second } == 1 }
                    .filter { it.first == it.second }
                    .map { it.first }
                    .joinToString(separator = "")
}