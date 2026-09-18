#!/usr/bin/env kotlin

package derry.s2


// 非空 断言操作符特点
fun main () {
    // 默认是不可空类型，不能随意给 null
    var name1: String?  = null

    val r4 = name1!!.capitalize()  // !!断言 不管name是不是null，都执行，和java一样
    println(r4)

    // 如果百分百能保证name是有值的
}
