#!/usr/bin/env kotlin

package derry.s6

import java.util.Objects

class KTBase108 <INPUT>(vararg objects: INPUT, val isR: Boolean = true) {
    // 开启 INPUT泛型的只读模式
    val objectArray: Array<out INPUT> = objects

    // 5种返回类型的变化
    fun getR1() : Array<out INPUT> ? = objectArray.takeIf { isR }

    fun getR2() : Any = objectArray.takeIf { isR } ?: "返回的null"

    fun getR3() : Any? = objectArray.takeIf { isR } ?: "返回的null" ?: null

    fun getR4(index: Int) : INPUT ? = objectArray[index].takeIf { isR }

    // INPUT Float Int Char String ... = Any ?
    fun getR5(index: Int) : Any = objectArray[index].takeIf { isR } ?: "AAA"  ?: 555 ?: 45.4f

    // 运算符重载
    operator fun get(index: Int): INPUT? = objectArray[index].takeIf { isR }
}

fun <INPUT> inputObj(item: INPUT) {

    // 泛型很大的范围类型，可以接收很多类型，也可以接收null
    // 异步处理泛型，都用String
    val length = (item as? String)?.length ?: "返回null"
    println(length)
}


//  []操作符
// 1. 5种返回类型变化
// 2. 给泛型传入null后，直接操作
fun main () {
    inputObj("Derry")

    val p1: KTBase108<String?> = KTBase108("张三", "王五", null)

    println(p1[1])
}


