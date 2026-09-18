#!/usr/bin/env kotlin

package derry.s4


// 解构语法过滤元素

fun main () {

    val list1 : List<String> = listOf("Derry", "zhangSan", "liSi")

    val(value1, value2, value3 ) = list1
    // value1 = "" val只读

    var(v1,v2,v3) = list1
    println("v1:$v1,v2:$v2,v3: $v3")


    var(_ ,n2,n3) = list1
    // _ 不是变量名，是用来过滤解构赋值的，不接收赋值给我

}


