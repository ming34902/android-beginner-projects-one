#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// 匿名函数
fun main () {
    val len = "Derry".count()
    println(len)

    // it 等价与 Derry 字符 Char
    val len2 = "Derry".count {
        it == 'r'
    }
    println(len2)

}
