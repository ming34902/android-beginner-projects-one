#!/usr/bin/env kotlin

package derry.s6


// 变换函数 flatMap
// map { it == 每个元素 T String int Char} // 把每个元素加入到 新集合里，然后返回新集合List<String>
// flatMap { it == 每个元素 T 集合1  集合2 集合3 } // 把每个元素(集合)加入到 新集合里，然后返回新集合List<List<String>>
fun main () {
    val list = listOf("Kotlin", "Java", "Python")

    val result = list.flatMap { it.toList() }

    println("result$result")

    // Example of flattening a list of lists
    val nestedList = listOf(listOf(1, 2), listOf(3, 4))
    val flattened = nestedList.flatMap { it }
    println("flattened$flattened")
}


