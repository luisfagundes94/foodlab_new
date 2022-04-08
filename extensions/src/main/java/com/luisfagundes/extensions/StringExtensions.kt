package com.luisfagundes.extensions

fun List<String>.convertListToString() = joinToString(", ")

fun String.Companion.empty() = ""

fun String.formatDate() = split("-").reversed().joinToString("/")