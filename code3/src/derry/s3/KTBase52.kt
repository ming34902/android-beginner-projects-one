#!/usr/bin/env kotlin

package derry.s3


// run内置函数
//  run函数的特点 字符串延时
// 具名函数判断长度 isLong
// 具名函数检测合格 showText
// 具名函数增加一个括号 mapText
// 具名函数输出内容
fun main () {
    val str = "Derry is ok"
    val  r1 : Float = str.run {
        // this == str本身
        true
        54540.4f
    }

    // 2.具名函数判断长度 isLong
    str.run {
        // this == str本身
    }

    // str.run(具名函数)
    str.run{::isLong}
        .run{::showText}
        .run{::mapText}

    // let函数持有it， 不能像 run()持有this 那么灵活，自动给下一个函数this
//    str.let{::isLong}
//        .let{::showText}
//        .let{::mapText}


    // str.run 匿名函数
    str.run {
        if (str.length > 5) true else false
    }.run {
        if (this) "字符串合格" else "字符串不合格"
    }.run {
        "结果1[$this]"
    }.run {
        println(this)
    }
}

fun isLong(str: String) /* Boolean */ = if (str.length > 5) true else false

fun showText(isLong: Boolean) /* String */ = if (isLong) "字符串合格" else "字符串不合格"

fun mapText(getShow: Boolean) /* String */ = "结果1[$getShow]"
