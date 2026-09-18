#!/usr/bin/env kotlin

package derry.s2
//val PI: Double = 3.1415 // 定义编译时的常量

// 函数作为返回类型
fun main () {
    val result1 = showAction1() // result1 是 showAction1函数的 返回值
    println(result1(99,12.2))


    val result2 = showAction2("信息2") // result2 是 showAction2函数的返回值，这个返回值是函数

}


public fun showAction1(): (Int, Double) -> String  {
    val  name = "Derry"
   return {
       age: Int, weight: Double ->
       val showInfo = "今年是xxx年"
       "最终返回: name:$name, ${showInfo}， age:$age, weight: $weight"
   }
}

fun showAction2(info: String) : String {
    return info
}
