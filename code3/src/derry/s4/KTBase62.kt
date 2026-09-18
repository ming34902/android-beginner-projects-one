#!/usr/bin/env kotlin

package derry.s4


// set创建与元素获取
// 1.set 定义 不允许重复
// 2. 普通方式 elementAt 会越界崩溃
// 3. elementAtOrElse elementAtOrNull

fun main () {

    val set1 : Set<String> = setOf("Derry", "zhangSan", "liSi")


    //    println(set1.elementAt(3)) // 会崩溃越界

    println(set1.elementAtOrElse(3) { "越界了"})

    println(set1.elementAtOrNull(3) ?: "越界了" )
}


