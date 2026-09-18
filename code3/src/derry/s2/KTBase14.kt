package derry.s2

import java.lang.IO.println

fun main() {
    val week = 10

    // KT 的 if/when 是表达式，有返回值
    // 我们让 when 表达式直接返回字符串，赋值给 info
    val info = when (week) {
        1 -> "今天是星期一"
        2 -> "今天是星期二"
        3 -> "今天是星期三"
        4 -> "今天是星期四"
        5 -> "今天是星期五"
        6 -> "今天是星期六"
        7 -> "今天是星期日"
        else -> "忽略星期几"
    }
    
    // 这样 info 就不再是 Unit (void)，而是具体的字符串
    println(info)
}
