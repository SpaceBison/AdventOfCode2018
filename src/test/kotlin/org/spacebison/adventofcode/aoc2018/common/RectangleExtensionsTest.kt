package org.spacebison.adventofcode.aoc2018.common

import com.natpryce.hamkrest.equalTo
import org.junit.Test
import java.awt.Rectangle

class RectangleExtensionsTest {
    @Test
    fun testRectangleSubtractInner() {
        val rect1 = Rectangle(0, 0, 6, 15)
        val rect2 = Rectangle(1, 3, 2, 5)

        val expected = setOf(
                Rectangle(0, 0, 1, 3),
                Rectangle(1, 0, 2, 3),
                Rectangle(3, 0, 3, 3),

                Rectangle(0, 3, 1, 5),
                Rectangle(3, 3, 3, 5),

                Rectangle(0, 8, 1, 7),
                Rectangle(1, 8, 2, 7),
                Rectangle(3, 8, 3, 7)
        )

        val result = rect1.subtract(rect2)

        println("in result but not expected:")
        println((result - expected).joinToString(separator = "\n"))

        println("expected but not in result:")
        println((expected - result).joinToString(separator = "\n"))

        com.natpryce.hamkrest.assertion.assert.that(result, equalTo(expected))
    }

    @Test
    fun testRectangleSubtractSimple() {
        val rect1 = Rectangle(1, 1, 2, 1)
        val rect2 = Rectangle(1, 0, 1, 2)

        val expected = Rectangle(2, 1, 1, 1)

        val result = rect1.subtract(rect2).filter { !it.isEmpty }

        println("in result but not expected:")
        println((result - expected).joinToString(separator = "\n"))

        com.natpryce.hamkrest.assertion.assert.that(result.single(), equalTo(expected))
    }

    @Test
    fun testRectangleSubtractNonIntersecting() {
        val rect1 = Rectangle(0, 0, 1, 1)
        val rect2 = Rectangle(0, 2, 1, 1)

        val expected = Rectangle(0, 0, 1, 1)

        val result = rect1.subtract(rect2).filter { !it.isEmpty }

        println("in result but not expected:")
        println((result - expected).joinToString(separator = "\n"))

        com.natpryce.hamkrest.assertion.assert.that(result.single(), equalTo(expected))
    }
}