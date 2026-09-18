#!/usr/bin/env kotlin

package derry.s3


// 数字类型的安全转换函数
fun main () {
    val number1: Int = "666".toInt()

    // 字符串里面放入Double类型，无法转换成 Int 会崩溃
    val number2: Int? = "6666.66".toIntOrNull()

    // toIntOrNull() 字符串变整形的安全函数转换
    val number3: Int? = "4444".toIntOrNull()
    println(number3 ?: "原来你是null啊")

}
