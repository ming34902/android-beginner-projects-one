#!/usr/bin/env kotlin

package derry.s4

class KTBase80  {
    var number1 = 88
    init {
        number1 = number1.times(88)
    }
    // var number1 = 88 // kt没办法搞好执行顺序
}

// 初始化陷阱
fun main () {
    println(KTBase80().number1)
}


