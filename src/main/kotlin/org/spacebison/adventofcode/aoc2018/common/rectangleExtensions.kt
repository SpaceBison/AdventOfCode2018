package org.spacebison.adventofcode.aoc2018.common

import java.awt.Rectangle

fun Rectangle.subtract(rectangle: Rectangle): Set<Rectangle> {
    val inter = intersection(rectangle)

    if (inter.isEmpty) {
        return setOf(this)
    }

    return setOf(
            Rectangle(x, y, inter.x - x, inter.y - y),
            Rectangle(x, inter.y, inter.x - x, inter.height),
            Rectangle(x, inter.y + inter.height, inter.x - x, y + height - inter.y - inter.height),

            Rectangle(inter.x, y, inter.width, inter.y - y),
            Rectangle(inter.x, inter.y + inter.height, inter.width, y + height - inter.y - inter.height),

            Rectangle(inter.x + inter.width, y, x + width - inter.x - inter.width, inter.y - y),
            Rectangle(inter.x + inter.width, inter.y, x + width - inter.x - inter.width, inter.height),
            Rectangle(inter.x + inter.width, inter.y + inter.height, x + width - inter.x - inter.width, y + height - inter.y - inter.height))
}