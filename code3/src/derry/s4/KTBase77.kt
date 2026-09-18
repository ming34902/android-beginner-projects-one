#!/usr/bin/env kotlin

package derry.s4

// 第一步: 生成 val sex: Char
class KTBase77 (_name: String, val sex: Char) {

    // 第二步 生成 val mName
    val mName = _name

    init {
        val nameValue = _name // 第三步 生成nameValue细节
        println("init代码块打印: nameValue:$nameValue")
    }

    // 次构造 三个参数的 必须调用主构造
    constructor(name: String, sex: Char, age: Int) : this(name, sex) {
        // 第五步 生成次构造的细节
        println("次构造 三个参数的，name:$name,sex: $sex, age:$age")
    }

    // 第四步
    val derry = "xcxxxx"
    // Derry顺序的正确： init代码块 和 类成员 是同时的，只不过在写 init代码块前面 就是先生成 类成员
}

// 构造函数 初始化顺序
// 1.main调用次构造 name sex age
// 2. 主构造 val变量
// 3.var mName = _name
// 4. init { nameValue 打印 }
fun main () {
    KTBase77("张三", 'M', 64)
}


