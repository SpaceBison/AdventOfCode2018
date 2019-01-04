package org.spacebison.adventofcode.aoc2018

import org.spacebison.adventofcode.aoc2018.common.allMinBy
import org.spacebison.adventofcode.aoc2018.common.getInputForDay

fun main(args: Array<String>) {
    val input = getInputForDay(7)
    println(Day7.part1(input))
    println(Day7.part2(input, 5, 60))
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
            val nextStep = steps.first { transitivePredecessors.get(it)?.isEmpty() ?: true }

            steps.remove(nextStep)
            transitivePredecessors.remove(nextStep)
            transitivePredecessors.forEach { step, preds -> preds.remove(nextStep) }
            order.add(nextStep)
        }

        return order.joinToString("")
    }

    fun part2(input: String, workers: Int, baseTaskTime: Int): Int {
        val rules = parseInput(input).toSet()

        val steps = rules.flatMap { it.toList() }.asSequence().distinct().sorted().toMutableList()

        val predecessors = rules.groupBy { it.second }.mapValues { it.value.map { it.first }.toMutableSet() }

        val transitivePredecessors =
                predecessors.mapValues {
                    val transitive = it.value.toMutableSet()
                    while (transitive.addAll(transitive.flatMap { predecessors.get(it) ?: mutableSetOf() }));
                    transitive
                }.toMutableMap()

        val currentTasks = mutableMapOf<String, Int>()
        var time = 0

        while (steps.isNotEmpty()) {
            repeat (workers - currentTasks.size) {
                val nextStep = steps.firstOrNull { transitivePredecessors.get(it)?.isEmpty() ?: true }

                nextStep?.let {
                    currentTasks.put(it, time + baseTaskTime + (it.first().toInt() - 'A'.toInt() + 1))
                    steps.remove(it)
                }
            }

            val completed = currentTasks.entries.allMinBy { it.value }
            completed.forEach {
                transitivePredecessors.remove(it.key)
                transitivePredecessors.forEach { step, preds -> preds.remove(it.key) }
            }
            currentTasks.entries.removeAll(completed)

            time = completed.first().value
        }

        return time
    }

    private fun parseInput(input: String) =
            input.lineSequence()
                    .mapNotNull { inputRegex.matchEntire(it)?.destructured }
                    .map { it.component1() to it.component2() }

}