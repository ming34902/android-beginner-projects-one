#!/usr/bin/env kotlin

package derry.s4

class KTBase76 (userName: String = "张三", userAge: Int, userSex: Char) {
    // 相当于 java的 {}构造代码块
    init {
        println("主构造函数被调用了$userName,$userAge,$userSex")

        // 如果第一个参数是false，就会调用第二个参数 lambda
        require(userName.isBlank()) {  "userName参数空值异常" }

        require(userAge > 0) {  "userAge年龄不符" }

        require(userSex == '男' || userSex == '女') {  "userSex性别异常" }
    }

    constructor(userName: String) : this(userName, 45, '男') {
        println("次构造函数被调用了")
    }

    fun show() {
        // println(userName)
    }
}

// 初始化模块
// 1. name age sex 主构造函数
// 2. init代码块 require
// 3. 临时类型只有在 init代码块才能调用
fun main () {
    KTBase76("李四", userAge = 55, userSex = 'M')
}