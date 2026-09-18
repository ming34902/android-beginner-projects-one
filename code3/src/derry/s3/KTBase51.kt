#!/usr/bin/env kotlin

package derry.s3


// let 内置函数
// 普通方式 对集合第一个元素相加
// let方式 对集合第一个元素相加
// 普通方式 对值判断null .并返回
// let方式 对值判断null .并返回
fun main () {
    // 普通方式 对集合第一个元素相加
    val list = listOf(6,5,2,45,5)
    val value1 = list.first() // 取第一个元素
    val result1 = value1 + value1
    println(result1)

    val result2 = listOf(6,5,2,45,5).let {
        // it == list 集合
        it.first() + it.first() // 匿名函数的最后一行，作为返回值，let的特点
        /* true  */
    }
    println(result2)

    // 普通方式， 对值判null,并返回
    println(getMethod1("Derry"))
}

fun getMethod1(value: String?) : String {
    return if (value == null) "传递的内容是null" else "成功返回$value"
}
// 简化版
fun getMethod2(value: String?) = if (value == null) "传递的内容是null" else "成功返回$value"

fun getMethod3(value: String?) : String {
    return value?.let {
        "成功返回$it"
    } ?:  "传递的内容是null"
}

fun getMethod4(value: String?) =   value?.let { "成功返回$it"} ?:  "传递的内容是null"