#!/usr/bin/env kotlin

package derry.s5


class Student1(var name: String, var age: Int,var sex: Char) {
   // 注意事项，  component0 顺序必须是 component1 component2 component3 和成员一一对应
   operator fun component1() = name
   operator fun component2() = age
   operator fun component3() = sex
}

data class Student2Data(var name: String, var age: Int,var sex: Char)

// 解构声明
fun main () {
   val(name, age, sex) = Student1("李四", 34, '男')
   println("$name, $age,$sex")

   val(name1, age1, sex1) = Student2Data("李四", 34, '男')

   val(_, age2, _) = Student1("李四", 34, '男')
}


