#!/usr/bin/env kotlin

package derry.s4

class KTBase75 (var name: String = "张三") {
    // 2个参数的 次构造函数，必须要调用主构造函数，否则不通过
    // 主构造统一管理，为了更好的初始化设计
    constructor(name: String = "王五", sex: Char = '男') : this(name) {
        println("2个参数的次构造函数name:$name,sex:$sex")
    }
    // 3个参数的次构造函数，必须要调用主构造函数
    constructor(name: String = "李四", sex: Char = '男',age: Int = 88): this(name) {
        println("2个参数的次构造函数name:$name,sex:$sex,age:$age")
    }
}

// 构造函数的 默认参数
fun main () {
    KTBase75()
}


