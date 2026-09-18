#!/usr/bin/env kotlin

package derry.s3


// 异常处理 先决条件函数
fun main () {

    var value1: String ? = null
    var value2: Boolean  = false

   // checkNotNull(value1) // java.lang.IllegalStateException: Required value was null

   // requiredNotNull(value1) // java.lang.IllegalArgumentException: Required value was null

    require(value2) // java.lang.IllegalArgumentException: Failed requirement
}

