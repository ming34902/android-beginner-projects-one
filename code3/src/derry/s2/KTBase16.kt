#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println

//val PI: Double = 3.1415 // 定义编译时的常量

fun main () {
    val age1 = 26
    val name1 = "老王"
    method01(age1,name1)
}
// 函数默认都是 public
// kotlin的函数 更规范的 先有输入，再有输出
private fun method01(age: Int, name:String) : Int {
    println("你姓名是:$name,你年龄是:$age")
    return 200
}
//

// 上面kt函数 会背后变成下面的 java代码
//private static final int method01(int age,String name) {
//    String var2 = "你姓名是:"+ name + ",你年龄是:" +age;
//    System.out.println(var2)
//    return 200
//}