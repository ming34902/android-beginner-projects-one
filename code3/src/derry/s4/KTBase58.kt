#!/usr/bin/env kotlin

package derry.s4


// 可变的 list集合
// 不可变集合 to 可便集合
// 可变集合 to 不可便集合
fun main () {

    val list5 = mutableListOf("Derry", "zhangSan", "liSi")
    list5.add("赵六")
    list5.remove("liSi")

    // 不可变集合 to 可便集合
    val list6 = listOf(454,56,565)
    // 无法进行可变操作 list.add  list.remove

    val list7 : MutableList<Int> = list6.toMutableList()
    // 可变集合，可以完成 可变操作
    list7.add(999)
    println("list7:$list7")


    val list8 : MutableList<Char> = mutableListOf('A', 'B', 'C')
    // 可变集合，可以完成 可变操作
    list8.add('Z')
    println("list8:$list8")

    val list9 : List<Char> = list8.toList()
    // toList之后，是 可变集合 to 不可便集合
    //    list9.add('G')
    println("list9:$list9")
}

