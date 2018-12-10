package org.spacebison.adventofcode.aoc2018

import org.junit.Test

import org.junit.Assert.*
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo

class Day7Test {

    val input = """
        Step C must be finished before step A can begin.
        Step C must be finished before step F can begin.
        Step A must be finished before step B can begin.
        Step A must be finished before step D can begin.
        Step B must be finished before step E can begin.
        Step D must be finished before step E can begin.
        Step F must be finished before step E can begin.""".trimIndent()

    @Test
    fun part1() {
        val result = Day7.part1(input)
        assert.that(result, equalTo("CABDFE"))
    }

    @Test
    fun part2() {
    }
}