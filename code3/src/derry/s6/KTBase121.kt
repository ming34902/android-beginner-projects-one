#!/usr/bin/env kotlin

package derry.s6

import derry.s6.com.randomItemValue as g1  // 重命名扩展
import derry.s6.com.randomItemValuePrintln as p1 // 重命名扩展

// 重命名扩展
fun main () {
    val list: List<String> = listOf("张三","李四", "王五")
    val set: Set<Double> = setOf(34.4, 43.5, 32.5)

    list.g1()
    set.p1()
}


