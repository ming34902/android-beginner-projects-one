#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// lambda 学习
fun main () {
    // 匿名函数 == lambda 表达式，类型推断为String
    val methodDE1 = {
       v1: Double, v2: Float, v3: Int ->
        "v1: $v1, v2: $v2, v3: $v3"
    }
    println(methodDE1(656454.45, 45454.4f, 55))

    // 匿名函数 == lambda 表达式，类型推断为int
    val methodDE2 = { v1: Double, v2: Float, v3: Int ->
        1 // 匿名函数放回的结果 == lambda成果
    }
    println(methodDE2(656454.45, 45454.4f, 55))


    // 匿名函数 == lambda 表达式，类型推断为 float
    val methodDE3 = { v1: Double, v2: Float, v3: Int ->
        45454.4f // 匿名函数放回的结果 == lambda成果
    }
    println(methodDE3(656454.45, 45454.4f, 55))


//    // 传统方式：传入接口实现
//    button.setOnClickListener(object : View.OnClickListener {
//        override fun onClick(v: View) {
//            println("Clicked!")
//        }
//    })
//
//    // Lambda 方式：直接传递代码块，极其简洁
//    button.setOnClickListener { println("Clicked!") }
}

// Lambda 表达式：语法极简
val sumLambda = { a: Int, b: Int -> a + b }

// 匿名函数：保留了 fun 关键字，显式指定类型和 return
val sumFun = fun(a: Int, b: Int): Int {
    return a + b
}


