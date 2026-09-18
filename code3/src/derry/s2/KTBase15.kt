#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println

//val PI: Double = 3.1415 // 定义编译时的常量

fun main () {
    val garden = "xxx info"
    val time = 6

    val isLogin = false
    // kotlin中 if是表达式,  java中if是语句，有局限性 不能在 String模板里这么写
    println("Server response result:${if (isLogin) "恭喜你登录成功" else "登录失败" }")
}