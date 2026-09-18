#!/usr/bin/env kotlin

package derry.s4

class KTBase73 (var name: String, val sex: Char, val age:Int, var info: String) {
    fun show() {
        println(name)
        // todo
    }
}

// 主构造函数里的定义属性
fun main () {
    KTBase73("张三", 'M', 64, "信息").show()
}


