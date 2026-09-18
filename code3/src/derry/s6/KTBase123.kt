#!/usr/bin/env kotlin

package derry.s6

import java.io.File


class Context {
    val info1 = "Derry"
    val name = "DDD"

    fun toast(str: String) = println("toast: $str")
}

inline fun Context.apply5(lambda: Context.(String) -> Unit): Context {
    lambda(info1)
    return  this
}

inline fun File.applyFile( action: (String, String?) -> Unit): File {
    setWritable(true)
    setReadable(true)
    action(name, readLines()[0])
    return this
}

// DSL  所谓DSL领域专用语言
fun main () {
    // 1.定义整个 lambda规则标准
    // 然后 main函数就可以根据DSL编程方式标准规则，来写具体的实现，就是DSL编程范式

    val context1 = Context().apply5 {
        toast("success1")
        toast(it)
        toast(name)
    }
    println(context1.info1)

    // applyFile 函数，就是DSL编程凡是，定义输入输出规则
    // 1. 定义整个 lambda规则标准，输入  必须是File类，才有资格调用 applyFile函数，匿名函数里面持有 fileName,data
    // 2. 定义整个 lambda规则标准，输出  始终返回File对象本身，所以可以链式调用
    val file1 : File = File("D:\\work-text\\java-bin.txt")
        .applyFile { fileName, data ->
            println("fileName$fileName, data:$data")
            true
        }.applyFile {
            a, b ->
            // todo
        }
    println("始终输出file本身${file1.name}")
}


