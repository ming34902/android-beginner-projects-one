#!/usr/bin/env kotlin

package derry.s4

//  this(name) 是主构造，先要调用主构造之后，才会调用剩下的次构造 sex age
class KTBase74 (var name: String) {
    // 2个参数的 次构造函数，必须要调用主构造函数，否则不通过
    // 主构造统一管理，为了更好的初始化设计
    constructor(name: String, sex: Char) : this(name) {
        println("2个参数的次构造函数name:$name,sex:$sex")
    }
    // 3个参数的次构造函数，必须要调用主构造函数
    constructor(name: String, sex: Char,age: Int): this(name) {
        println("2个参数的次构造函数name:$name,sex:$sex,age:$age")
    }
}

// 次构造函数里 来完成函数重载
fun main () {

    KTBase74("张三")
    KTBase74("李四", 'M')
    KTBase74("王五", 'M', 88)
}


