#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// 匿名函数的类型推断
fun main () {
    // 匿名函数类型推断为String
    val methodD1 = {
       v1: Double, v2: Float, v3: Int ->
        "v1: $v1, v2: $v2, v3: $v3"
    }
    println(methodD1(656454.45, 45454.4f, 55))
    // 方法名 : 方式写。参数类型 和 返回类型 必须带上，例如：  : (Int, Int, Int) -> String = {}
    // 方法名 = 类型推断返回类型


    // 匿名函数，类型推断为int
    val methodD2 = {
      v1: Double, v2: Float, v3: Int -> 1
    }
    println(methodD2(656454.45, 45454.4f, 55))

    // 匿名函数，类型推断为float
    val methodD3 = {
            v1: Double, v2: Float, v3: Int -> 34434.1f
    }
    println(methodD3(656454.45, 45454.4f, 55))
}
