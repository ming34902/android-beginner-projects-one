#!/usr/bin/env kotlin

package derry.s3

import java.io.File


// apply 内置函数 持有this == 持有的字段内容本身
fun main () {
    val info1 = "Derry you hao"

    println("info最后一个字符是:${info1[info1.length - 1]}")
    println("info转小写:${info1.toLowerCase()}")
    // info1.apply  apply始终返回info本身String 类型，后面可以 链式调用
    val infoNew : String = info1.apply {
        // 一般大部分情况下，匿名函数，都会持有一个it,但apply 函数不会持有it ,当前this == info本身
        println("apply匿名函数打印的:$this")
    }
    println("apply的返回值:$infoNew")

    // 普通写法
    val file1 = File("D:\\work-text\\java-bin.txt")
    file1.setExecutable(true)
    file1.setReadable(true)
    println(file1.readLines())

//    file1.apply {
//        setExecutable(true)
//    }.apply {
//        setReadable(true)
//    }.apply {
//        println(file1.readLines())
//    }

}
