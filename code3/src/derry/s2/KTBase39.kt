#!/usr/bin/env kotlin

package derry.s2


// 对比if判断null 值的情况
fun main () {
    // 默认是不可空类型，不能随意给 null
    var name1: String?  = null

    // name1.capitalize() // name是可空类型，可能是null， 想要使用name,必须给出补救措施

    if(name1 != null) {
        val r5 = name1.capitalize()
        println(r5)
    } else {
        println("name is null")
    }
}
