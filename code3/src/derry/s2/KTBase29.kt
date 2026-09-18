#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println


// 在函数中定义参数是函数中的函数
fun main () {
    loginApi("Derry","123456") {
        msg: String, code: Int ->
        println("登录结果： msg: $msg,code: $code")
    }
}

const val DB_SAVE_USER_NAME = "Derry"
const val DB_SAVE_USER_PWD = "123456"

// 前端模仿登录 登录api
private fun loginApi(userName: String, userPwd: String, serverResponse: (String, Int) -> Unit) {
    // 校检
    if (userName.length > 3 && userPwd.length > 3) {
        // todo
        if (webServiceLoginAPI(userName,userPwd)) {
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
private fun webServiceLoginAPI(name: String, pwd: String) : Boolean {
    // Kotlin 的if 是表达式，JAVA的 if是语句 ， js中的 if是语句
    return name == DB_SAVE_USER_NAME && pwd == DB_SAVE_USER_PWD
}

private fun doLogin(userName: String, userPwd: String, serverResponse: (String, Int) -> Unit) {
    if (userName == null || userPwd == null) {
       // todo
        serverResponse("用户或密码为空", 200)
    }
    // 校检
    if (userName.length > 3 && userPwd.length > 3) {
        // todo

    } else {
        serverResponse("用户或密码不合格", 200)
    }
    if (DB_SAVE_USER_NAME == userName && userPwd == DB_SAVE_USER_PWD) {
        // todo
        val serverResponseResult = "恭喜你，登录成功"
        serverResponse(serverResponseResult, 200)
    } else {
        // todo
        val  serverResponseResult = "登录失败"
        serverResponse(serverResponseResult, 404)
    }
}
