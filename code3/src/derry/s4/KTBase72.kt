#!/usr/bin/env kotlin

package derry.s4

// 主构造函数，规范来说，都是增加 _xxx的方式,临时输入类型不能直接用，需要转换接收 成为变量才能用
class KTBase72(_name: String, _sex: Char, _age: Int, _info: String) {
    var name1 = _name
        get() = field
        private set(value) {
            field = value
        }
    var sex1 = _sex
        get() = field
    //   private set(value) {
    //     field = value
    //   }
    // 只读不能修改，不能set函数定义

    val age1 = _age
        get() = field +1

    val info1 = _info
        get() = "[${field}]"

    fun show1() {
        println(name1)
    }
}

// 主构造函数
fun main () {
    val p1 = KTBase72(_name = "Zhangsan", _info = "做些事", _age = 88, _sex = 'M')
    println(p1.name1)


}


