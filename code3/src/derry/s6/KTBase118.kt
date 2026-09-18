#!/usr/bin/env kotlin

package derry.s6

// 对 String? == 可空类型的 进行函数扩展，并且有备用值
fun String?.outputStringValueFun(defalutValue: String) = println(this ?: defalutValue)

fun String?.outputStringValueFunGet(defalutValue: String) = if (this == null) defalutValue else this

// 可空类型扩展函数
// 如果是null就输出默认值
fun main () {
    val infoValue: String ? = null
    // infoValue是可空类型 String ?== 可空类型的
    infoValue.outputStringValueFun("XX1")

    // String? 可以接收 可空数据，也可以接收 有值数据
    // String  只能接收 有值数据
    val name1 = "Derry"
    name1.outputStringValueFun("XXX2")

    infoValue.outputStringValueFunGet("xxx3")
}


