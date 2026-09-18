#!/usr/bin/env kotlin

package derry.s6

// 整个 SetClass1 里面所有成员 泛型相关只能修改 更改，不能读取
class SetClass1<in T>() {
    // 200个函数 对T只能修改，不能给外界， in T  out T 声明处指定关系，声明泛型处， 这个Java是没有该功能的
    fun set1(item : T) {
        println("set1--item:$item")
    }

    // 增加 in 后不能给外界读取，所以编译不通过
//    fun get1() :T? {
//        return null
//    }

}

class GetClass1<out T>(_item: T) {

    val item: T = _item

    // 增加 out 后不能修改，所以编译不通过
//    fun set1(item : T) {
//        println("set1--item:$item")
//    }

    fun get1() :T? {
        return item
    }

}

// in 和 out 应用
fun main () {
    // 逆变 in T SetClass1 只能修改，不能给外界读取
    val p1 = SetClass1<String>()
    p1.set1("张三")

    // 协变 out T GetClass1 只能读取，不能修改
    val p2 = GetClass1<String>("王五")
    p2.get1()
}


