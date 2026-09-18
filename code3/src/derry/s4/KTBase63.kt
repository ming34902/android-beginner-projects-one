#!/usr/bin/env kotlin

package derry.s4


// 可变的 Set集合

fun main () {

    val set1 : MutableSet<String> = mutableSetOf("Derry", "zhangSan", "liSi")

    set1 += "赵六"
    set1.add("刘军")

}


