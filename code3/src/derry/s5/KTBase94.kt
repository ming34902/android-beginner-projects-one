#!/usr/bin/env kotlin

package derry.s5

class AddClass1(number1: Int, number2: Int)

data class AddClass2(var number1: Int, var number2: Int) {
    operator fun plus(p1: AddClass2):Int {
        return (number1 + p1.number1) + (number2 + p1.number2)
    }
}
// 运算符重载
fun main () {
    // c++  +运算符重载就行
    // kotlin  plus 关键字代表 +运算符重载
    println(AddClass2(1,2) + AddClass2(3, 4))
}


