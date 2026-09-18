#!/usr/bin/env kotlin

package derry.s5

// Kotlin所有的类，默认是final修饰符，不能被继承，和java相反
// open 移除final修饰符
open class Person(val name: String, i: Int) {
    private fun showName() = "父类的名字是：$name"

    open fun myPrintln() = println(showName())
}

class Student (val subName: String, age: Int) : Person(subName, 78) {
    private fun showName() = "子类的名字是：$subName"

    override fun myPrintln() = println(showName())
}

//  继承与重载的 open关键字
fun main () {
   val person1: Person = Student("张三", 67)
    person1.myPrintln()
}


