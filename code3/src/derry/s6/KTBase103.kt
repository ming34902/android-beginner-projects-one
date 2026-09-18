#!/usr/bin/env kotlin

package derry.s6

class KTBase103<T>(private  val obj: T) {
    fun show() = println("输出$obj")
}

data class Student(val name: String, val age: Int, val sex: Char)
data class Teacher(val name: String, val age: Int, val sex: Char)
// 定义泛型
// 1.定义对象输出器
// 2. 定义两个对象 三个属性
// 3. 对象 String Int Double Float Char 等
fun main () {
    val stu1 = Student("张三", 45, '男')
    val tea1 = Teacher("张三", 45, '男')

    KTBase103(stu1).show()
    KTBase103(tea1).show()
}


