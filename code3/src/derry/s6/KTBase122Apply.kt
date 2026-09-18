#!/usr/bin/env kotlin

package derry.s6

import java.io.File


// private 私有化
// inline 因为是高阶函数，需要使用内联 对lambda 进行优化处理，提高性能
// fun <INPUT> 函数重声明一个泛型
// INPUT.mApply 让所有的类型，都可以  xxx1.mApply 泛型扩展
// INPUT.() -> Unit 让匿名函数里面持有this,在 lambda里面不需要返回值，因为永远都返回INPUT本身
// lambda(this) 默认this
//  返回this的目的是可以链式调用
private inline fun <INPUT> INPUT.mApply(lambda: INPUT.() -> Unit) : INPUT {
    lambda() // lambda(this)  可以省略this
    return this
}

// apply函数
fun main () {
    val r: File = File("D:\\work-text\\java-bin.txt")
        .mApply {
            // 输入的是 this == file 对象本身
            this.setReadable(true)

            println(readLines())
        }.mApply {
            // apply永远返回链式对象本身，可以一直链式调用下去
        }


    File("D:\\work-text\\java-bin.txt").apply {
        // todo
    }
}


