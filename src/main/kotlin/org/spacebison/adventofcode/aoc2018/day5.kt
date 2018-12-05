package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.getInputForDay
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val input = getInputForDay(5)
    println(Day5.part1(input))
    println(Day5.part2(input))
}

object Day5 {
    val upperToLower = 'a' - 'A'

    fun part1(input: String): Int {
        val polymer = input.toCharArray().toMutableList()

        return collapse(polymer).size
    }

    fun part2(input: String): Int? {
        val polymer = input.toCharArray().toMutableList()

        val unitTypes = polymer.asSequence().map { it.toLowerCase() }.distinct()

        return unitTypes
                .map { type -> polymer.toMutableList().apply { removeAll { it.toLowerCase() == type } } }
                .map { collapse(it).size }
                .min()
    }

    private fun collapse(polymer: MutableList<Char>): MutableList<Char> {
        var indices: List<Int> = firstReducible(polymer)

        while (indices.isNotEmpty()) {
            polymer.apply {
                indices.asReversed().forEach {
                    polymer.removeAt(it + 1)
                    polymer.removeAt(it)
                }
            }

            indices = firstReducible(polymer)
        }

        return polymer
    }

    private fun firstReducible(polymer: MutableList<Char>) =
            (0 until polymer.size - 1)
                    .filter { (polymer[it] - polymer[it + 1]).absoluteValue == upperToLower }
                    .run { filter { !contains(it + 1) } }
}
