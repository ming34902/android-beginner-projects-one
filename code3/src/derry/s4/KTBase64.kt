#!/usr/bin/env kotlin

package derry.s4


// 集合转换与快捷函数
// 1.定义可变list集合
// 2.List 转 Set 去重
// 3.List 转 Set 转 List 也能去重
// 4.快捷函数去重 distinct
fun main () {

    // list 可以有重复的元素
    val list1 : MutableList<String> = mutableListOf("Derry", "zhangSan", "liSi", "Derry")

    // list 转 set 去重
    val set1 = list1.toSet()

    // List 转 Set 转 List 也能去重
    val list2 = list1.toSet().toList()

    // 内部做了： 先转变成 可变的 set集合  再转换成 list集合
    println(list1.distinct())
    println(list1.toMutableSet().toList()) // 与 distinct() 等价

}


