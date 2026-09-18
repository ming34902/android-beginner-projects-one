#!/usr/bin/env kotlin

package derry.s6


// 合并函数 zip
fun main () {
    val names = listOf("张三", "李四", "王五")
    val ages =  listOf(23, 44, 55)

    // java  zip合并操作符
    // Kotlin自带  zip合并操作符
    // 原理 把第一个集合第二个集合合并起来，创建新的集合，并返回
    // 创建新的集合  元素Pair<K,V>

    val zip2: List<Pair<String,Int>> = names.zip(ages)
    println("zip2$zip2")

    println("zip2.toMap${zip2.toMap()}")
//    println("zip2.toMutableSet${zip2.toMutableSet()}")
//    println("zip2.toMutableList${zip2.toMutableList()}")
    zip2.forEach {
        // it == Pair<K,V>
        println("姓名：${it.first}, 年龄：${it.second}")
    }
}


