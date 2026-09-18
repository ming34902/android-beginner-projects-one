#!/usr/bin/env kotlin

package derry.s3


// takeIf 内置函数
// ABCDEFGHIJKMNOPQRSTUVWXYZ
fun main () {
    val result1 = checkPermissionAction("Root", "!@#$")
    println("欢迎${result1}用户登录系统")
    // name.takeIf { true / false }
    // true 直接返回name本身
    // false 直接返回 null


    // takeIf + 空合并操作符
}


// 前端
public fun checkPermissionAction(name: String, pwd: String): String? {
    return name.takeIf { permissionSystem(name, pwd) }
}


// takeIf + 空合并操作符
public fun checkPermissionAction2(name: String, pwd: String): String {
    return name.takeIf { permissionSystem(name, pwd) } ?: "你的权限不够"
}

//  权限系统
private  fun permissionSystem(username: String, userpwd: String): Boolean {
    return if (username == "Root" && userpwd == "!@#$") true else false
}