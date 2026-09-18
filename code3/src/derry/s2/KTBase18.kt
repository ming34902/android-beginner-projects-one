#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// java语言的void关键字，void是无参数返回的 忽略类型，但是它是关键帧，不是类型
// unit不屑，默认它有，无参数返回的 忽略类型 == Unit类型类
fun main () {
    val age1 = 26
    val name1 = "老王"
    loginAction2()
}
// 具名函数
private fun loginAction2() : Unit {
    return println()
}

private fun loginAction3() {
     println()
}
