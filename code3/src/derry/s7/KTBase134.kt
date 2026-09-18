#!/usr/bin/env kotlin

package derry.s7

class MyObject {
    companion object {
        @JvmField
        val TARGET = "黄石公园"

        @JvmStatic
        fun showAction(name: String) = println("name:$name")
    }
}

// 注解 @JvmStatic 和 kotlin 之间调用
fun main () {
    MyObject.showAction("Derry")
}


