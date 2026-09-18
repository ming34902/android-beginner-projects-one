#!/usr/bin/env kotlin

package derry.s6

// 条件1   对第一个参数 C1.gogo  函数扩展
// 条件2   需要在 括号(c2: C2) 参数里面，传递一个参数
private infix fun <C1,C2> C1.gogo(c2: C2) {
    // todo
    println("中缀表达式，对第一个参数内容是$this")
    println("中缀表达式，对第二个参数内容是$c2")
}

// infix关键字
// infix == 中缀表达式   可以简化代码  (与c++ 中缀表达式一样)
fun main () {

    "Derry" gogo('M')
    223 gogo 'M'
    3434.gogo('M')
}


