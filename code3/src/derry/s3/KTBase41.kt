#!/usr/bin/env kotlin

package derry.s3


// 异常处理 与自定义异常特点
fun main () {
    try {
        var name7: String?  = null
        checkException(name7)
        println(name7!!.length)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun checkException(info: String?) {
    info ?: throw CustomException()
}

class CustomException : IllegalArgumentException("你的代码有异常")