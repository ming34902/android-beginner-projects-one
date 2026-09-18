#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println



fun main () {
    val age1 = 26
    val name1 = "老王"
    loginAction(
        name1, "12122121", "13497794121",
        age = age1,
        name = name1)
}
// 具名函数
 private fun loginAction(userName: String, password: String, phoneNumber: String, age: Int, name:String) : Int {
    println("userName:$userName, password:$password,phoneNumber:$phoneNumber,,你姓名是:$name,你年龄是:$age")
    return 200
}
