#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// it关键字特点
fun main () {
    // 函数输入输出的声明 合并，第二部 根据刚刚的声明 写实现： ==匿名函数
    val methodActionC1 : (Int, Int, Int) -> String = { number1, number2, number3 -> "Derry"}
    // methodActionC1(9000, 555, 34344) === methodActionC1,invoke(9000, 555, 34344)
    println(methodActionC1(9000, 555, 34344))

    val methodActionC2 : (String) -> String = {"$it Derry"}
    println(methodActionC2("我就是"))

/*
* fun methodActionC2(it: String) -> String = { return "$it" }
*
* */
    val methodActionC3 : (Double) -> String = {"$it Ok"}
    println(methodActionC3(656454.45))
    // 匿名函数参数只有一个参数时，it就代表这个参数的参数名称，类型会根据整个参数的类型变化
}
