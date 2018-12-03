package org.spacebison.adventofcode.aoc2018

import java.io.BufferedReader

fun getInputForDay(day: Int) = getInputForDay(day.toString())

fun getInputForDay(day: String): String =
        ClassLoader.getSystemResourceAsStream("day$day").bufferedReader().use { it.readText() }
