#!/usr/bin/env kotlin

package derry.s7


//
fun main () {
    val javaClass = derry.s6.KTBase129()
    val info1 = javaClass.info1
    val info2 = javaClass.info2

    println(info1?.length)
    println(info2?.length)
    println(info1?.length ?: 0)
    println(info2?.length ?: -1)
}


