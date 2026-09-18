#!/usr/bin/env kotlin

package derry.s3

import kotlin.math.roundToInt


// Double类型 转 Int的类型格式化
// 654.5665656
fun main () {
    println(654.5665656.toInt()) // 655 四舍五入
    println(654.5665656.roundToInt()) // 655 四舍五入

    // roundToInt() 保障 Double类型 转int 按四舍五入

    // 保留3位小数位
    val r3 = "%.3f".format(654.5665656)
}
