#!/usr/bin/env kotlin

package derry.s3


// takeUnless 内置函数
// takeUnless 和 takeIf 的功能是相反的
// name.takeIf { true/false }  true: 返回name本身，false: 返回null
// name.takeUnless { true/false }  false: 返回name本身，true: 返回null
class Manager {
    private var infoValue : String ? = null

    fun getInfoValue() /* :String? */ = infoValue

    fun setInfoValue(infoValue: String ) {
        this.infoValue = infoValue
    }
}


fun main () {
    val manager = Manager()

    // manager.setInfoValue("AAAA")

    // takeUnless +  it.isNullOrBlank() 一起使用，验证字符串有没有进行初始化等功能
    val r6 = manager.getInfoValue().takeUnless {
        it.isNullOrBlank()
    } ?: "未经过初始化的值"

    println(r6)
}
