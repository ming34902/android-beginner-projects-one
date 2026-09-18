#!/usr/bin/env kotlin

package derry.s4


// List创建与元素获取
// 普通取值 索引
// 防止崩溃取值方式 getOrElse getOrNull
fun main () {

    val list5 = listOf("Derry", "zhangSan", "liSi")

    // printIn(list5[3]) // 崩溃 java.lang.arrayIndexOutOfBoundsException : 4
    // 防止 空指针异常 下标越界异常 的方式：  getOrElse getOrNull

    println(list5.getOrElse(3) {  "越界了" })

    println(list5.getOrNull(4) ?:  "没有该下标")
}

