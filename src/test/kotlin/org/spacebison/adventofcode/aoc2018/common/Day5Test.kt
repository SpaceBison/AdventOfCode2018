package org.spacebison.adventofcode.aoc2018.common

import org.junit.Test

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.spacebison.adventofcode.aoc2018.Day5

class Day5Test {
    val input = "dabAcCaCBAcCcaDA"

    @Test
    fun part1() {
        val result = Day5.part1(input)

        assert.that(result, equalTo(10))
    }

    @Test
    fun part2() {
        val result = Day5.part2(input)

        assert.that(result, equalTo(4))
    }
}