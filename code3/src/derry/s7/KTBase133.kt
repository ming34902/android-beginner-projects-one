#!/usr/bin/env kotlin

package derry.s7

fun show(name: String, age: Int = 29, sex: Char = 'M') {
    println("name$name,age:$age,sex$sex")
}

@JvmOverloads
fun toast(name: String, sex: Char = 'M') {
    println("name$name,sex$sex")
}

// 注解  @JvmOverloads 与kotlin
fun main () {
    show("张三",33)
}


