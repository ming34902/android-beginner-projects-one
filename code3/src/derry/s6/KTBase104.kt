#!/usr/bin/env kotlin

package derry.s6

import kotlin.io.println

class KTBase104<T>(private  val isR: Boolean, private  val obj: T) {
    fun getObj() = obj.takeIf { isR }
}

// 泛型函数
// 1. 万能对象返回启 Boolean 来空值是否返回 运用 takeIf
// 2. 4个对象打印
// 3. 对象打印 + run + ?:
// 4. 对象打印 + apply + ?;
// 5. show(t: T) + apply + ?:
fun main () {
    val stu1 = Student("张三", 45, '男')
    val tea1 = Teacher("张三", 45, '男')

    KTBase104(true,stu1).getObj()
    KTBase104(true,tea1).getObj()
    KTBase104(false,tea1).getObj() ?: "返回null"

    val r1: Any = KTBase104(true, stu1).getObj() ?.run {
        // 如果 getObj() 返回有值，就会进来
        // this == getObj
        println("万能对象是:$this")
    } ?: println("返回的是null")

    // apply 永远返回 getObj.apply  getObj本身 , !!断言 不会返回null
    val r2: Student = KTBase104(true, stu1).getObj().apply {}!!

    val r3: Teacher = KTBase104(true, tea1).getObj().apply {
        // this == getObj
        if (this == null) {
            // 返回null
        } else {
            println("万能对象是:$this")
        }
    }!!


    show1("Derry")
    show2(null)
}

// show(t: T) + apply + ?:
fun <B> show1(item: B) {
    item ?.also {
        // it == item 本身
        println("万能对象是:$it")
    } ?: println("返回的是null")
}

fun <B> show2(item: B) {
    val r = item ?.also {
        if (it == null) {
            println("返回的是null")
        } else {
            println("万能对象是:$it")
        }
    } ?:  println("返回的是null")
}
