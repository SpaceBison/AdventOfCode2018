package org.spacebison.adventofcode.aoc2018

import org.junit.Test
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo

internal class Day4Test {
    val input = """
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-01 23:58] Guard #99 begins shift
            [1518-11-02 00:40] falls asleep
            [1518-11-02 00:50] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
            [1518-11-04 00:02] Guard #99 begins shift
            [1518-11-04 00:36] falls asleep
            [1518-11-04 00:46] wakes up
            [1518-11-05 00:03] Guard #99 begins shift
            [1518-11-05 00:45] falls asleep
            [1518-11-05 00:55] wakes up
        """.trimIndent()

    @Test
    fun part1() {
        val result = Day4.part1(input)

        assert.that(result, equalTo(240L))
    }

    @Test
    fun part2() {
        val result = Day4.part2(input)

        assert.that(result, equalTo(4455L))
    }
}