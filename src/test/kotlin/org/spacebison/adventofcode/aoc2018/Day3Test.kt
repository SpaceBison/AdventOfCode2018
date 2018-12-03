package org.spacebison.adventofcode.aoc2018

import com.natpryce.hamkrest.equalTo
import org.junit.Test
import com.natpryce.hamkrest.assertion.assert

class Day3Test {

    @Test
    fun testPart1() {
        val input = """
            #1 @ 1,3: 4x4
            #2 @ 3,1: 4x4
            #3 @ 5,5: 2x2""".trimIndent()

        val result = Day3.part1(input)

        assert.that(result, equalTo(4))
    }
}