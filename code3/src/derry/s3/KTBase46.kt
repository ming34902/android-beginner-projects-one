#!/usr/bin/env kotlin

package derry.s3


// == 与 === 比较操作
fun main () {
    // == 值 内容的比较，相当于java的 equals
    // ===  引用的比较
    val name1 = "Derry"
    val name2 = "Derry"
    println(name1.equals((name2)))
    println(name1 == name2)
}
