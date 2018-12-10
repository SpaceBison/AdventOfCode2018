package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.getInputForDay

fun main(args: Array<String>) {
    val input = getInputForDay(7)
    println(Day7.part1(input)) // wrong: "SBCJRHKUTVWDQAIGYOPXMFNZEL"
    println(Day7.part2(input))
}

object Day7 {
    val inputRegex = "Step (.+) must be finished before step (.+) can begin.".toRegex()

    fun part1(input: String): String {
        val rules = parseInput(input).toSet()

        val steps = rules.flatMap { it.toList() }.asSequence().distinct().sorted().toMutableList()

        val stepCount = steps.size

        val predecessors = rules.groupBy { it.second }.mapValues { it.value.map { it.first }.toMutableSet() }

        val transitivePredecessors =
                predecessors.mapValues {
                    val transitive = it.value.toMutableSet()
                    while (transitive.addAll(transitive.flatMap { predecessors.get(it) ?: mutableSetOf() }));
                    transitive
                }.toMutableMap()

        val order = mutableListOf<String>()

        while (order.size < stepCount) {
            transitivePredecessors.forEach { t, u -> println("$t -> $u") }

            val nextStep = steps.first { transitivePredecessors.get(it)?.isEmpty() ?: true }
            println("+ $nextStep")

            steps.remove(nextStep)
            transitivePredecessors.remove(nextStep)
            transitivePredecessors.forEach { step, preds-> preds.remove(nextStep) }
            order.add(nextStep)

            println(order)
            println()
        }

        return order.joinToString("")
    }

    private fun parseInput(input: String) =
            input.lineSequence()
                    .mapNotNull { inputRegex.matchEntire(it)?.destructured }
                    .map { it.component1() to it.component2() }

    fun part2(input: String) {}

}