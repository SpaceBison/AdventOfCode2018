package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.getInputForDay

fun main(args: Array<String>) {
    val input = getInputForDay(8)
    println(Day8.part1(input))
    println(Day8.part2(input))
}

object Day8 {

    fun part1(input: String): Int {
        val numbers = input.split(' ').map { it.toInt() }
        val tree = readTree(numbers)
        return tree.flattened().flatMap { it.metadata }.sum()
    }

    fun part2(input: String): Int {
        val numbers = input.split(' ').map { it.toInt() }
        val tree = readTree(numbers)
        return tree.value()
    }

    private fun readTree(numbers: List<Int>): Node {
        val childCount = numbers[0]
        val metadataCount = numbers[1]

        var offset = 0
        val children = (0 until childCount).map {
            val child = readTree(numbers.drop(offset + 2))
            offset += child.flattened().map { it.metadata.size + 2 }.sum()
            child
        }

        return Node(children, numbers.subList(offset + 2, offset + 2 + metadataCount))
    }

    data class Node(val children: List<Node>, val metadata: List<Int>) {
        fun flattened(): List<Node> = listOf(this) + children.flatMap { it.flattened() }

        fun value(): Int =
                if (children.isEmpty()) {
                    metadata.sum()
                } else {
                    metadata.mapNotNull { children.getOrNull(it - 1) }.sumBy { it.value() }
                }
    }
}