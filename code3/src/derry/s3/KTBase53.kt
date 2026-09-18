#!/usr/bin/env kotlin

package derry.s3


// with 内置函数
fun main () {
    val str = "爱说啥阿萨"
//    具名函数
    val r1 = with(str, ::getStrLen)
    val r2 = with(r1, ::getLenInfo)
    val r3 = with(r2, ::getInfoMap)
    println(r3)

    // 匿名函数
    with(with(with(with(str) {
        length
    }) {
        "字符串长度是：$this"
    }) {
        "是：$this"
    }) {
      println(this)
    }
}

fun getStrLen(str: String) = str.length

fun getLenInfo(len: Int) = "字符串长度是：$len"

fun getInfoMap(info: String) = "是：$info"
