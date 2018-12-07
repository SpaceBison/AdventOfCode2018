package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.allMinBy
import org.spacebison.adventofcode.aoc2018.common.getInputForDay
import java.awt.Point
import java.awt.Rectangle
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val input = getInputForDay(6)
    println(Day6.part1(input))
    println(Day6.part2(input))
}

object Day6 {

    fun part1(input: String): Int? {
        val seeds = parseInput(input)
        val bbox = seeds.fold(Rectangle(seeds.first())) { rect, point -> rect.also { rect.add(point) } }

        val pointCounts = IntArray(seeds.size)
        val outerSeedIds = mutableSetOf<Int>()

        bbox.points.forEach { point ->
            seeds.mapIndexed { index, seed -> index to point.manhattanDistanceTo(seed) }
                    .allMinBy { it.second }
                    .singleOrNull()
                    ?.first
                    ?.also {
                        if (point.x == bbox.x || point.y == bbox.y || point.x == bbox.x + bbox.width || point.y == bbox.y + bbox.height) {
                            outerSeedIds.add(it)
                        }
                    }
                    ?.let { pointCounts[it] = pointCounts[it] + 1 }
        }

        return pointCounts.filterIndexed { index, i -> !outerSeedIds.contains(index) }.max()
    }

    private fun parseInput(input: String) =
            input.lineSequence()
                    .map { it.split(", ") }
                    .map { Point(it[0].toInt(), it[1].toInt()) }
                    .toList()

    fun part2(input: String) {}

    private fun Point.manhattanDistanceTo(point: Point) =
            (x - point.x).absoluteValue + (y - point.y).absoluteValue

    private val Rectangle.points
        get() = (x..(x + width)).flatMap { pointX ->
            (y..(y + height)).map { pointY ->
                Point(pointX, pointY)
            }
        }
}