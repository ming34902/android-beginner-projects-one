#!/usr/bin/env kotlin

package derry.s2


// 空合并操作符
fun main () {
    // 默认是不可空类型，不能随意给 null
    var name6: String?  = "李四"
    name6 = null

    // 空合并操作符   xxx ?: “要执行的内容a”
    // 如果xxx等于 null,就会执行 ?; 后面的区域
    println(name6 ?: "要执行的内容a")

    // let + 空合并操作符
    println(name6?.let { "[$it]" ?: "要执行的内容a"  })

}
