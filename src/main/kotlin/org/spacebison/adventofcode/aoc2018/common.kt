package org.spacebison.adventofcode.aoc2018

import java.awt.Rectangle
import java.lang.IllegalArgumentException

fun getInputForDay(day: Int) = getInputForDay(day.toString())

fun getInputForDay(day: String): String =
        ClassLoader.getSystemResourceAsStream("day$day").bufferedReader().use { it.readText() }

fun getNumberSequence(input: String): Sequence<Int> {
    return input.split(Regex("[\\s,]"))
            .asSequence()
            .filter { it.isNotEmpty() }
            .map { Integer.parseInt(it) }
}

fun <T> Sequence<T>.looping() =
        generateSequence { this }.flatMap { it }

fun <T> Sequence<T>.pairPermutations() =
        flatMap { first -> map { second -> first to second } }

fun <T> Sequence<T>.pairCombinations() =
        mapIndexed { i, first -> drop(i + 1).map { second -> first to second } }.flatMap { it }

fun <T> Iterable<T>.pairPermutations() =
        flatMap { first -> map { second -> first to second } }

fun <T> Iterable<T>.pairCombinations() =
        mapIndexed { i, first -> drop(i + 1).map { second -> first to second } }.flatMap { it }

fun Rectangle.subtract(rectangle: Rectangle): Rectangle {
    val intersection = intersection(rectangle)

    return when {
        intersection.width == 0 || intersection.height == 0 -> this

        x == intersection.x && width == intersection.width -> {
            val y = if (intersection.y == y) intersection.y + intersection.height else y
            Rectangle(x, y, width, height - intersection.height)
        }

        y == intersection.y && height == intersection.height -> {
            val x = if (intersection.x == x) intersection.x + intersection.width else x
            Rectangle(x, y, width - intersection.width, height)
        }

        else -> throw IllegalArgumentException("Cannot subtract $rectangle from $this; intersection: $intersection")
    }
}