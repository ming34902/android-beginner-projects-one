#!/usr/bin/env kotlin

fun main(array: Array<String>) {
    var name: String ? = null

    // 第一种补救措施 name如果真的是null, 后面不执行 就不会引发空指针异常
    // name?.length

    // 第二种补救措施 无论name是不是null 都执行 (java的写法一样)
    // name!!.length

    // 第三种 java的一样
    if (name != null) name.length
}

/*
* String 字符串
* Char 单字符
* Boolean true false
* Int 整数
* Double 小数
* List 元素集合
* Set 无重复的元素集合
* Map 键对值的集合
* */