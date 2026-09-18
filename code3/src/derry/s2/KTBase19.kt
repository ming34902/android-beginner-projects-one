#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// Noting类型特点
fun main () {
    val age1 = 26
    val name1 = "老王"
    show1(-1)
}
private fun show1(number: Int) {
     when(number) {
         -1 -> TODO("没有这种分数") // Noting 结束当前程序
         in 0..59 -> println("不及格")
         in 60..80 -> println("及格")
         in 81..100 -> println("优秀")
     }
}
interface A1 {
    fun show1()
}

class AImpl:  A1 {
    override fun show1() {
        // 下面这句话 不是注释提示，会终止程序
        TODO("Not yet implemented")
    }
}