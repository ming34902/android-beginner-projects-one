#!/usr/bin/env kotlin

package derry.s6


// 互操作性和 可空性
fun main () {
    // Example: Interacting with a Java platform type
//    val name: String? = null
//    val length = name?.length ?: 0
//    println("Length is: $length")

    // java与Kotlin 错误交互案例
    println(KTBase129().info1.length)
    println(KTBase129().info2.length)

    // java与Kotlin 错误交互案例
    // :String! java 与kotlin 交互时候，java给kotlin用的值都是 String!
    // 只要看见 String! 的类型，在使用时候 必须是 ?.xxx
    val info1 = KTBase129().info1
    val info2 = KTBase129().info2
    println(info1?.length)
    println(info2?.length)

    // :String! java与kotlin 交互时候，java给kotlin用的值都是 String!
    // 只要看见 String! 的类型，在使用时候 必须是 String? 来接收 java值
    val info1ss : String? = KTBase129().info1
    val info2ss : String? = KTBase129().info2
    println(info1ss?.length)
    println(info2ss?.length)
}


