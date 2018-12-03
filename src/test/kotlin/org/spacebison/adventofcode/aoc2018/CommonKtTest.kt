package org.spacebison.adventofcode.aoc2018

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Test

internal class CommonKtTest {

    @Test
    fun pairPermutations() {
        val sequence = sequenceOf(1, 2, 3)

        val pairPermutations = sequence.pairPermutations()
        val expected = setOf(
                1 to 1, 1 to 2, 1 to 3,
                2 to 1, 2 to 2, 2 to 3,
                3 to 1, 3 to 2, 3 to 3)

        assert.that(pairPermutations, equalTo(expected))
    }

    @Test
    fun pairCombinations() {
        val sequence = sequenceOf(1, 2, 3, 4)

        val pairCombinations = sequence.pairCombinations().map { setOf(it.first, it.second) }.toSet()
        val expected = setOf(
                setOf(1, 2), setOf(1, 3), setOf(1, 4),
                setOf(2, 3), setOf(2, 4),
                setOf(3, 4))

        assert.that(pairCombinations, equalTo(expected))
    }
}