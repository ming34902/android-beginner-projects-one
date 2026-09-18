#!/usr/bin/env kotlin

package derry.s6


// 使用函数式编程
fun main () {

    val names = listOf("张三", "李四", "王五")
    val ages =  listOf(23, 44, 55)

    val zip = names.zip(ages)
    zip.forEach {
        println("姓名：${it.first}, 年龄：${it.second}")
    }

//    val result = names.zip(ages).toMap().map { (name, age) ->
//        "you name:$name, you age:$age"
//    }
//    println(result)
}


