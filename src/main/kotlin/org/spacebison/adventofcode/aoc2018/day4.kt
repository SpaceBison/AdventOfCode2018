package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.getInputForDay
import java.time.Duration
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

fun main(args: Array<String>) {
    val input = getInputForDay(4)
    println(Day4.part1(input))
    println(Day4.part2(input))
}

object Day4 {
    val timeParser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val shiftStartRegex = "Guard #(\\d+) begins shift".toRegex()
    val sleepStart = "falls asleep"
    val sleepEnd = "wakes up"

    data class Sleep(val start: LocalDateTime, val end: LocalDateTime)
    data class Shift(val guardId: Int, val start: LocalDateTime, val sleeps: List<Sleep>)

    fun part1(input: String): Long {
        val shifts = parseRecord(input)

        val shiftsByGuard = shifts.groupBy { it.guardId }
                .mapValues { it.value.flatMap { it.sleeps } }

        val mostSleepyGuard = shiftsByGuard
                .mapValues { it.value.map { Duration.between(it.start, it.end).toMinutes() }.sum() }
                .maxBy { it.value }!!
                .key

        val mostSleepyMinute = shiftsByGuard[mostSleepyGuard]
                ?.flatMap { (it.start.minute until it.end.minute) }
                ?.groupBy { it }
                ?.values
                ?.maxBy { it.size }
                ?.first()

        return mostSleepyGuard.toLong() * mostSleepyMinute!!
    }

    fun part2(input: String): Long {
        val shifts = parseRecord(input)

        val shiftsByGuard = shifts.groupBy { it.guardId }
                .mapValues { it.value.flatMap { it.sleeps } }

        val (mostFrequentMinute, mostFrequentSleeper) = shiftsByGuard
                .mapValues { it.value.flatMap { it.start.minute until it.end.minute } }
                .flatMap { entry -> entry.value.map { it to entry.key } }
                .groupBy { it.first to it.second }
                .mapValues { it.value.count() }
                .maxBy { it.value }!!
                .key


        return mostFrequentMinute.toLong() * mostFrequentSleeper
    }

    private fun parseRecord(input: String): LinkedList<Shift> {
        val record = input.lineSequence()
                .associate { it.substring(1..16) to it.substring(19) }
                .mapKeys { LocalDateTime.parse(it.key, timeParser) }
                .toSortedMap()

        var currentGuard: Int? = null
        var currentShiftStart: LocalDateTime = LocalDateTime.now()
        var currentSleepStart: LocalDateTime = LocalDateTime.now()
        val currentSleeps = LinkedList<Sleep>()
        val shifts = LinkedList<Shift>()

        record.forEach {
            when {
                it.value == sleepStart -> currentSleepStart = it.key
                it.value == sleepEnd -> currentSleeps.add(Sleep(currentSleepStart, it.key))
                it.value.matches(shiftStartRegex) -> {
                    currentGuard?.let {
                        shifts.add(Shift(it, currentShiftStart, currentSleeps.toList()))
                        currentSleeps.clear()
                    }

                    currentGuard = shiftStartRegex.matchEntire(it.value)!!.destructured.component1().toInt()
                    currentShiftStart = it.key
                }
            }
        }

        shifts.add(Shift(currentGuard!!, currentShiftStart, currentSleeps.toList()))

        return shifts
    }
}