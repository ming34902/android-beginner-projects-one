#!/usr/bin/env kotlin

package derry.s4


// mutator 函数
// 1. mutator +=  -=
// 2. removeIf
fun main () {

    val list1 : MutableList<String> = mutableListOf("Derry", "zhangSan", "liSi")
    list1 += "赵六"
    list1 -= "Derry"
    println(list1)

    // removeIf
    list1.removeIf { true } // 如果是true  自动遍历可便集合，进行一个元素 一个元素的输出
    list1.removeIf { it.contains("Der") } // 过滤所有元素， 只要有 Der的元素，就是true 删除
}

