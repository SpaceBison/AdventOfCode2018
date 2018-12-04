package org.spacebison.adventofcode.aoc2018

import java.awt.Rectangle
import java.util.*

fun main(args: Array<String>) {
    val input = getInputForDay(3)
    println(Day3.part1(input))
    println(Day3.part2(input))
}

object Day3 {

    fun part1(input: String): Int {
        val rectangles = input.lineSequence()
                .map { it.split('@', '#', ' ', 'x', ':', ',').filter { it.isNotBlank() }.map { it.toInt() } }
                .map { Rectangle(it[1], it[2], it[3], it[4]) }
                .toSet()

        println("rectangles: ${rectangles}")

        val intersections = rectangles
                .pairCombinations()
                .filter { it.first.intersects(it.second) }
                .map { it.first.intersection(it.second) }
                .toSet()

        println("intersections: ${intersections}")

        val trimmedIntersections = LinkedList<Rectangle>()

        intersections.forEachIndexed { index, rect ->
            println("trim $rect")
            trimmedIntersections
                    .also { println("with $it") }
                    .foldRight(rect) { intersection, acc -> acc.subtract(intersection).also { println("acc $it") } }
                    .let { trimmedIntersections.add(it) }
        }

        println("trimmed: $trimmedIntersections")

        return trimmedIntersections.map { it.width * it.height }.sum()
    }

    fun part2(input: String) {}
}