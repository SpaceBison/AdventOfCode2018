package org.spacebison.adventofcode.aoc2018

import java.awt.Rectangle

fun main(args: Array<String>) {
    val input = getInputForDay(3)
    println(Day3.part1(input))
    println(Day3.part2(input))
}

object Day3 {

    fun part1(input: String) =
            input.lineSequence()
                    .map { it.split('@', '#', ' ', 'x', ':', ',').filter { it.isNotBlank() }.map { it.toInt() } }
                    .map { Rectangle(it[1], it[2], it[3], it[4]) }
                    .also { println("rects: ${it.count()}") }
                    .pairCombinations()
                    .also { println("combinations: ${it.count()}") }
                    .asSequence()
                    .filter { it.first.intersects(it.second) }
                    .also { println("intersecting: ${it.count()}") }
                    .map { it.first.intersection(it.second) }
                    .run {
                        val sum = map { it.width * it.height }.sum()
                        println("sum: $sum")
                        val intersectingSum = pairCombinations()
                                .also { println("intersection combinations: ${it.count()}") }
                                .asSequence()
                                .filter { it.first.intersects(it.second) }
                                .also { println("intersections intersecting: ${it.count()}") }
                                .map { it.first.intersection(it.second) }
                                .map { it.width * it.height }
                                .sum()
                        return@run sum - intersectingSum
                    }

    fun part2(input: String) {}
}