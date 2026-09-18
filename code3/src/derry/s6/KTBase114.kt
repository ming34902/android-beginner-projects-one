#!/usr/bin/env kotlin

package derry.s6


data class ResponseResult1(val msg: String, val code: Int)
data class ResponseResult2(val msg: String, val code: Int)
data class ResponseResult3(val msg: String, val code: Int)
data class ResponseResult4(val msg: String, val code: Int)

fun Any.showPrintlnContent() = println("当前内容是：$this")
fun Any.showPrintlnContent2() {
    println("当前内容是：$this")
}

// 超类上定义扩展函数
// 1. 扩展函数不允许被重复定义
// 2. 对超类扩展函数的影响
// 3. 扩展函数 链式调用
fun main () {
    ResponseResult1("login success", 200).showPrintlnContent()

    "Derry1".showPrintlnContent().showPrintlnContent2()
}

// kotlin 内置的扩展函数，被重复定义，属于覆盖，而且优先使用我们自己定义的扩展汉拿山
/**
public fun File.readLines(charset: Charset = Charset.UTF_8) : List<String> {
    val result1 = ArrayList<String>()
    forEachLine(charset) { result1.add(it) }
    return  result1
}
*/
