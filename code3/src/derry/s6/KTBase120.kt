#!/usr/bin/env kotlin

package derry.s6

import derry.s6.com.randomItemValue
import derry.s6.com.randomItemValuePrintln


// 语言扩展文件
fun main () {
    val list: List<String> = listOf("张三","李四", "王五")
    val set: Set<Double> = setOf(34.4, 43.5, 32.5)

    // 如果不使用扩展文件
    println(list.shuffled().first())

    // 如果使用扩展文件
    println(list.randomItemValue())
    println(set.randomItemValuePrintln())
}


