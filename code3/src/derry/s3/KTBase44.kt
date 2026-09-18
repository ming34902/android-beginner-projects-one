#!/usr/bin/env kotlin

package derry.s3


//  split
fun main () {

    val jsonText = "Derry,leo,lance"
    // list 自类型推断 成 list == List<String>
    val list1 = jsonText.split(",")

    println("split分割后的list集合：$list1")

    val (v1, v2, v3) = list1
    // 解构
}

