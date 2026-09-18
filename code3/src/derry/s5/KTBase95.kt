#!/usr/bin/env kotlin

package derry.s5

enum class Week {
    星期一,
    星期二,
    星期三,
    星期四,
    星期五,
    星期六,
    星期日,
}
// 枚举
fun main () {
    println(Week.星期一)

    // 枚举的值 等价与 枚举本身
    println(Week.星期二 is Week)
}


