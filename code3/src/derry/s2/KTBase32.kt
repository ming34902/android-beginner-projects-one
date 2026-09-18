#!/usr/bin/env kotlin

package derry.s2
//val PI: Double = 3.1415 // 定义编译时的常量

fun main () {
    val obj1 = ::methodResponseResult1
    val obj2 = obj1
    val obj3 = obj2

//    loginApi5("derry", "12333", ::methodResponseResult1)
        loginApi5("derry", "12333", obj3)
}


fun methodResponseResult1(msg: String, code: Int)  {
   println("最终返回: msg:$msg， code:$code")
}

const val DB_SAVE_USER_NAME5 = "Derry"
const val DB_SAVE_USER_PWD5 = "123456"

// 如果此函数，不使用内联，在调用段，会生成多个对象来完成lambda的调用（会造成性能损耗）
// 前端模仿登录 登录api
public inline fun loginApi5(userName: String, userPwd: String, serverResponse: (String, Int) -> Unit) {
    // 校检
    if (userName.length > 3 && userPwd.length > 3) {
        // todo
        serverResponse("login success", 200)
    } else {
        serverResponse("用户或密码不合格", 200)
    }
}