#!/usr/bin/env kotlin

package derry.s5


open class Person2(val name: String) {
    fun showName() = "父类的名字是：$name"

    // Kotlin所有的类，默认是final修饰符，不能被继承被重写，和java相反
    open fun myPrintln() = println(showName())
}

class Student2 (private val subName: String) : Person2(subName) {
    fun showName2() = "子类的名字是：$subName"

    override fun myPrintln() = println(showName2())
}

//  类型转换
// 1.普通运行子类输出
// 2. is person student file
// is + as转换
fun main () {
   val person2: Person2 = Student2("李四")
    person2.myPrintln()

    println(person2 is Person2)

    // is + as
    if (person2 is Student2) {
        (person2 as Student2).myPrintln()
    }

    // is + as
    if (person2 is Person2) {
        (person2 as Person2).showName()
    }
}


