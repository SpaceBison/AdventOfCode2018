package org.spacebison.adventofcode.aoc2018

import org.junit.Test

import org.junit.Assert.*
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo

class Day8Test {

    val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"

    @Test
    fun part1() {
        val result = Day8.part1(input)
        assert.that(result, equalTo(138))
    }

    @Test
    fun part2() {
        val result = Day8.part2(input)
        assert.that(result, equalTo(66))
    }
}