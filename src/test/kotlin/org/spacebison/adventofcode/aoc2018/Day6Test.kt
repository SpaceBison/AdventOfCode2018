package org.spacebison.adventofcode.aoc2018

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class Day6Test {

    val input = """
        1, 1
        1, 6
        8, 3
        3, 4
        5, 5
        8, 9""".trimIndent()

    @Test
    fun part1() {
        val result = Day6.part1(input)
        assert.that(result, equalTo(17))
    }

    @Test
    fun part2() {
    }
}