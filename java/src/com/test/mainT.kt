package com.test

import com.aire.Exercise011

object mainT {
    @JvmStatic
    fun main(args: Array<String>) {
        val a = setOf("a", "b") == setOf("b", "a")
        println(a)
    }
}