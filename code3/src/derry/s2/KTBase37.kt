#!/usr/bin/env kotlin

package derry.s2


// 使用带let的安全调用
fun main () {

    // 默认是不可空类型，不能随意给 null
    var name3: String?  = null
    name3 = "derry"

    // name是可空类型，如果真的是null
    var r3 = name3?.let {
        // it == name 本身
        // 如果能够执行到这里面的。it一定不为null
        if (it.isBlank()) { // 如果name是空值
            "Default"
        } else {
            "[$it]"
        }
    }

}
