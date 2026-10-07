#!/usr/bin/env kotlin

//val PI: Double = 3.1415 // 定义编译时的常量

// kotlin 反编译后的字节码
fun main () {
    val info = "Derry info" // 整个成为 制度类型的变量

    // const 不适用于 局部变量
}
/**
 * kotlin 只有一种数据类型，
 * 看起来都是引用数据类型，实际上编译器会在JAVA字节码中，修改成基本类型
 *
 * */