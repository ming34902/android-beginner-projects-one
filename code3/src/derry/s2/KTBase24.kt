#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// 函数类型&隐式返回
fun main () {
    // 函数输入输出的声明
    val methodAction : () -> String
    // 根据刚刚的 声明，写实现 == 匿名函数体
    methodAction = {
        val inputValue = 999
        "$inputValue Derry" // == return  "$inputValue Derry"00
        // 匿名函数不需要 写 return,最后一行就是返回值
    }
    // 调用此函数
    println(methodAction())

}
