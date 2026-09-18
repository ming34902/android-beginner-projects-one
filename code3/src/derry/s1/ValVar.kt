#!/usr/bin/env kotlin

fun main(args: Array<String>) {
    // todo val 可读不可改
    val name: String = "Derry"

    // 不可读
    //  name = "Derry2"

    // 可读
    println("name:$name")

    // todo Var 可读可改
    var sex:Char = 'M'
    // 可改
    sex = 'A'
    // 可读
    println("sex:$sex")

    // todo Val 与 Var 使用厂家
    // 尽量使用Val,如果此变量后续需要 可改 就用 Var
}