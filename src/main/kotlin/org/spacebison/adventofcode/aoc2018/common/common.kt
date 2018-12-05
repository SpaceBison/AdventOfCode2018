package org.spacebison.adventofcode.aoc2018.common

fun getInputForDay(day: Int) = getInputForDay(day.toString())

fun getInputForDay(day: String): String =
        ClassLoader.getSystemResourceAsStream("day$day").bufferedReader().use { it.readText() }

fun getNumberSequence(input: String): Sequence<Int> {
    return input.split(Regex("[\\s,]"))
            .asSequence()
            .filter { it.isNotEmpty() }
            .map { Integer.parseInt(it) }
}

