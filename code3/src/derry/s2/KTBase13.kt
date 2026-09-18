#!/usr/bin/env kotlin

val PI: Double = 3.1415 // 定义编译时的常量

// kotlin 反编译后的字节码
fun main () {
    val numOne = 148

    // range范围从 哪里到哪里
    if(numOne in 10..59) {
        println("不及格")
    } else if (numOne in 0..9) {
        println("不及格且差")
    } else if (numOne in 60..100) {
        println("合格")
    }else if (numOne !in 0..100) {
        println("不在0-100f范围内")
    }
}
/**
 * kotlin 只有一种数据类型，
 * 看起来都是引用数据类型，实际上编译器会在JAVA字节码中，修改成基本类型
 *
 * */