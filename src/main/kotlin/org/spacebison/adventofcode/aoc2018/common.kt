package org.spacebison.adventofcode.aoc2018

import java.io.BufferedReader

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