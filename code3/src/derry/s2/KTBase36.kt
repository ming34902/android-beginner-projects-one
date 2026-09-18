#!/usr/bin/env kotlin

package derry.s2


// 安全调用操作符
fun main () {

    // 默认是不可空类型，不能随意给 null
    var name1: String?  = "Derry"
    name1 = null

    // name.capitalize() // name是可空类型，可能是null， 想要使用name,必须给出补救措施

    name1?.capitalize() // ?.后面这一段代码可不执行，就不会引起空指针异常

}
