#!/usr/bin/env kotlin

package derry.s4


// list集合遍历

fun main () {

    val list1  = listOf(1,2,3,4,5,6,7)

    for (i in list1) {
        println("元素:$i")
    }


    list1.forEach {
        // it == 每一个元素
        println("元素：$it")
    }

    list1.forEachIndexed  {
            item, index ->
        println("下标$index,元素$item")
    }
}


