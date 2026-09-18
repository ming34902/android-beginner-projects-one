#!/usr/bin/env kotlin

package derry.s5



data class LoginRequest(var info: String)

// 数据类使用条件
// 1. 服务器请求回来 响应的javaBean  LoginResponseBean  基本上可以使用 数据类
// 2. 数据类 至少必须有一个参数的主构造函数
// 3. 数据类必须有参数，var val 参数
// 4. 数据类不能使用 abstract open sealed inner 等修饰符，只做数据载入进行数据存储
fun main () {

}


