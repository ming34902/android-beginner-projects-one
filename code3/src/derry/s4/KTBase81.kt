#!/usr/bin/env kotlin

package derry.s4

class KTBase81  {
    val info1: String

    init {
        info1 = "XXXX-ok"
        getInfoMethod()
    // info1 = "XXXX-ok" // 不能写后面，  KTBase81().进行初始化时 因为info1还未赋值就调用（getInfoMethod()）已崩溃
    }

    fun getInfoMethod() {
        println("info${info1[0]}")
    }
}

// 初始化陷阱
fun main () {
    KTBase81().getInfoMethod()
}


