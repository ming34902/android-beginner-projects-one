#!/usr/bin/env kotlin

package derry.s2


// 可空性特点
fun main () {

    // 默认是不可空类型，不能随意给 null
    var name1: String  = "Derry"

    var name2: String ? = null

    // 第一种补救措施 name如果真的是null, 后面不执行 就不会引发空指针异常
    // name?.length

    // 第二种补救措施 无论name是不是null 都执行 (java的写法一样)
    // name!!.length

    // 第三种 java的一样
    if (name2 != null) name2.length
}
