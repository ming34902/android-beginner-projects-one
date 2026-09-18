#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// 在函数中定义参数是函数中的函数
fun main () {
    // 第一种
    loginApi3("Derry","123456", {
        msg: String, code: Int ->
        println("登录结果： msg: $msg,code: $code")
    })
    // 第二种
    loginApi3("Derry","123456", serverResponse = {
            msg: String, code: Int ->
        println("登录结果： msg: $msg,code: $code")
    })
    // 第三种
    loginApi3("Derry","123456") {
        msg: String, code: Int ->
        println("登录结果： msg: $msg,code: $code")
    }
}

const val DB_SAVE_USER_NAME3 = "Derry"
const val DB_SAVE_USER_PWD3 = "123456"

// 前端模仿登录 登录api
private fun loginApi3(userName: String, userPwd: String, serverResponse: (String, Int) -> Unit) {
    // 校检
    if (userName.length > 3 && userPwd.length > 3) {
        // todo
        if (webServiceLoginAPI3(userName,userPwd)) {
            // todo
            serverResponse("login success", 200)
        } else {
            // todo
            serverResponse("login fail", 200)
        }
    } else {
        serverResponse("用户或密码不合格", 200)
    }
}

// 提供后端服务 登录api
private fun webServiceLoginAPI3(name: String, pwd: String) : Boolean {
    // Kotlin 的if 是表达式，JAVA的 if是语句
    return name == DB_SAVE_USER_NAME3 && pwd == DB_SAVE_USER_PWD3
}

