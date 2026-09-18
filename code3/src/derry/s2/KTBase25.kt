#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// 语言的函数参数
fun main () {
    // 函数输入输出的声明 合并，第二部 根据刚刚的声明 写实现： ==匿名函数
    val methodActionB : (Int, Int, Int) -> String = { number1, number2, number3 ->
        val inputValue = 999
        "$inputValue Derry,参数一： $number1， 参数二： $number2 ， 参数三： $number3"

    }
    // 调用此函数
    println(methodActionB(9000, 555, 34344))

}
