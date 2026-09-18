#!/usr/bin/env kotlin

package derry.s5

// Kotlin所有的类，默认是final修饰符，不能被继承，和java相反
// open 移除final修饰符
open class Person3(val name: String, val age: Int) {

    private fun showName() = "父类的名字是：$name"

    open fun myPrintln() = println(showName())

    fun methodPerson() = println("父类方法") // 父类独有函数
}

class Student3 (val subName: String) : Person3(subName, 78) {
//    private fun showName() = "子类的名字是：$subName"

    override fun myPrintln() = println("子类显示：$subName")

    fun methodStudent() = println("子类方法") // 子类独有函数
}

//  智能类型转换
fun main () {
   val person1: Person3 = Student3("李四")
    person1.myPrintln()

    (person1 as Student3).methodStudent() // 这一次 记录 类型转换

    person1.methodStudent() // 这次就明白是 Student3的
}


