package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.getInputForDay
import org.spacebison.adventofcode.aoc2018.common.pairCombinations
import org.spacebison.adventofcode.aoc2018.common.subtract
import java.awt.Rectangle
import java.util.*

fun main(args: Array<String>) {
    val input = getInputForDay(3)
    println(Day3.part1(input))
    println(Day3.part2(input))
}

object Day3 {

    fun part1(input: String): Int {
        // parse rectangles
        val rectangles = input.lineSequence()
                .map { it.split('@', '#', ' ', 'x', ':', ',').filter { it.isNotBlank() }.map { it.toInt() } }
                .distinct()
                .map { Rectangle(it[1], it[2], it[3], it[4]) }
                .toSet()

        // find intersections
        val intersections = rectangles
                .pairCombinations()
                .filter { it.first.intersects(it.second) }
                .map { it.first.intersection(it.second) }
                .toSet()

        // collection of non-overlapping intersections
        val trimmed = HashSet<Rectangle>()
        intersections.forEach { intersection ->
            // for each consecutive intersection subtract the area belonging to previously stored intersections
            trimmed
                    .fold(setOf(intersection)) { acc, rectangle ->
                        acc.flatMap { it.subtract(rectangle).filter { it.isEmpty.not() } }.toSet()
                    }
                    .let { trimmed.addAll(it) }
        }

        // get sum of areas
        return trimmed
                .map { it.width * it.height }
                .sum()
    }

    fun part2(input: String): Int {
        data class Claim(val id: Int, val rectangle: Rectangle)

        // parse rectangles
        val rectangles = input.lineSequence()
                .map { it.split('@', '#', ' ', 'x', ':', ',').filter { it.isNotBlank() }.map { it.toInt() } }
                .distinct()
                .map { Claim(it[0], Rectangle(it[1], it[2], it[3], it[4])) }
                .toSet()

        return rectangles.first { rect -> rectangles.minus(rect).none { rect.rectangle.intersects(it.rectangle) } }.id
    }
}