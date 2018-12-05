package org.spacebison.adventofcode.aoc2018.common

fun <T> Sequence<T>.looping() =
        generateSequence { this }.flatMap { it }

fun <T> Sequence<T>.pairPermutations() =
        flatMap { first -> map { second -> first to second } }

fun <T> Sequence<T>.pairCombinations() =
        mapIndexed { i, first -> drop(i + 1).map { second -> first to second } }.flatMap { it }

fun <T> Iterable<T>.pairPermutations() = asSequence().pairPermutations().asIterable()
fun <T> Iterable<T>.pairCombinations() = asSequence().pairCombinations().asIterable()