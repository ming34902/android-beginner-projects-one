#!/usr/bin/env kotlin

package derry.s2
//val PI: Double = 3.1415 // 定义编译时的常量

// 匿名函数与具名函数
fun main () {
    // 匿名函数
    showPersonInfo("张三", 33, '男') {
        println("结果：$it")
    }

    // 具名函数 showPersonImpl
  showPersonInfo("张三", 33, '男', ::showPersonImpl)

}

fun showPersonImpl(result: String) {
    println("结果:$result")
}

inline fun showPersonInfo(name: String, age: Int, sex: Char, showResult: (String) -> Unit) {
    val str = "name:$name,age:$age,sex:$sex"
    showResult(str)
}



