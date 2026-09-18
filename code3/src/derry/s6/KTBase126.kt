#!/usr/bin/env kotlin

package derry.s6


// 过滤函数 filter
fun main () {
    val nameLists = listOf(
        listOf("张三", "李四", "王五"),
        listOf("jenny", "rain", "tony"),
        listOf("金钟", "金四", "金五")
    )

    // Filter names that start with "金"
    val filtered = nameLists.flatten().filter { it.startsWith("金") }
    println("filtered: $filtered")

    nameLists.flatMap {
        // 进来了3次
        it -> it.filter {
            // 进来了9次
            it.contains("金")
        }
    }.map {
        println("it$it")
    }
}


