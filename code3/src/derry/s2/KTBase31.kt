#!/usr/bin/env kotlin

package derry.s2

import java.lang.IO.println

/*
* 在 Kotlin 中，为了支持 Lambda，编译器会在底层自动创建 Function 接口的匿名对象。这意味着：
* 每一次调用接受 Lambda 的函数，都会在内存中额外创建一个对象。如果在循环或高频调用的地方频繁使用 Lambda，
* 会导致性能下降和 GC（垃圾回收）压力。函数内联（inline 关键字）就是为了解决这个性能损耗问题。
*
* 避坑提示：不要盲目给所有函数都加 inline。如果函数体非常庞大且在多处被调用，频繁复制代码会导致最终生成的代码体积（APK 大小）膨胀。
它最适合用于以函数类型作为参数的高阶函数（例如 Kotlin 标准库里的 map、filter、let 等）。
* */
// 如果函数参数lambda，尽量使用 inline 修饰的高阶函数



// 函数内联
fun main () {
    // 第一种
    loginApi4("Derry","123456", {
        msg: String, code: Int ->
        println("登录结果： msg: $msg,code: $code")
    })
}

const val DB_SAVE_USER_NAME4 = "Derry"
const val DB_SAVE_USER_PWD4 = "123456"

// 如果此函数，不使用内联，在调用段，会生成多个对象来完成lambda的调用（会造成性能损耗）
// 前端模仿登录 登录api
public inline fun loginApi4(userName: String, userPwd: String, serverResponse: (String, Int) -> Unit) {
    // 校检
    if (userName.length > 3 && userPwd.length > 3) {
        // todo
        if (webServiceLoginAPI4(userName,userPwd)) {
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
fun webServiceLoginAPI4(name: String, pwd: String) : Boolean {
    // Kotlin 的if 是表达式，JAVA的 if是语句
    return name == DB_SAVE_USER_NAME4 && pwd == DB_SAVE_USER_PWD4
}

