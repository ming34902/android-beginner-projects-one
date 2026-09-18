#!/usr/bin/env kotlin

package derry.s3

import java.io.File


// also 内置函数
// ABCDEFGHIJKMNOPQRSTUVWXYZ
fun main () {

    var str5 = "ABCDEFGHIJKMNOPQRSTUVWXYZ"
    val r1 :String = str5.also {
        true
        34564.4f
        'C'
    }

    val r2 :Int = 1233344.also {
        true
        34564.4f
        'C'
        false
    }

    str5.also {
        // it == str 本身
    }

    str5.also {
        println("Str的原始数据是：$it")
    }

    // str.also特点 also函数始终返回 str本身，所以可以用链式调用
    str5.also {
        println("str原始数据：$it")
    }.also {
        println("str转换小写的效果是${it.toLowerCase()}")
    }.also {
        println("end")
    }

    // 普通写法
    val file2 = File("D:\\work-text\\java-bin.txt")
    val sourceFile = file2.also {
        file2.setExecutable(true)
        file2.setReadable(true)
        println(file2.readLines())
    }.also {
        // todo
    }
    // sourceFile 没有任何影响
}

