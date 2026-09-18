#!/usr/bin/env kotlin

package derry.s6

fun <T> T.showContentInfo() = println("${ if (this is String) "字符串$length" else "不是字符串内容是：$this" }")

fun commonFun() {}

fun <INPUTTYPE> INPUTTYPE.showTypesAction() = run {
    when(this) {
        is String -> "你是String类型"
        is Int -> "你是Int类型"
        else -> "未知类型"
    }
}

// 泛型扩展函数
// 1. String类型输出长度
// 2. 显示调用时间
// 3. 显示调用者的类型
fun main () {
    343.showContentInfo()
    false.showContentInfo()
    commonFun().showContentInfo()


    println(343.4f.showTypesAction())
}


