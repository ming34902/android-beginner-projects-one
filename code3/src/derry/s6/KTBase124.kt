#!/usr/bin/env kotlin

package derry.s6


// 变换函数-map
fun main () {
    val list = listOf("张三","李四", "王五")

    // 原理 就是把匿名函数 最后一行的返回值加入一个新的集合，新集合的泛型是R，并且返回新集合
    val list2: List<Int> = list.map {
        // it = T == 元素 = String
        "[$it]"
        88
    }

    println(list2)
    // 用途 和 java的思路一样
    list.map {
        "姓名是$it"
    }.map {
        "$it，文字长度是${it.length}"
    }.map{
        "是$it"
    }
}


