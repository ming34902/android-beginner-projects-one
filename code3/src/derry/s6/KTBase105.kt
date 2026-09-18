#!/usr/bin/env kotlin

package derry.s6

import derry.s5.Person3
import derry.s5.Student


class KTBase105<T> (val isMap: Boolean = false, val inputType: T) {
    // 模仿RxJava T是要输入的 变化泛型， R是 变换后输出的类型

    // 要去map返回的类型是 R? == 有可能是R 有可能是null
//    inline fun <R> map(mapAction: (T) -> R) : R? = mapAction(inputType).takeIf { isMap }
    inline fun <R> map(mapAction: (T) -> R) = mapAction(inputType).takeIf { isMap }
}

// 上面的简化版本
inline fun<I, O> map(inputValue : I , isMap: Boolean = true, mapActionLambda: (I) -> O) =
     if (isMap) mapActionLambda(inputValue) else null

// 泛型类型变化
// 1. 类 isMap map takeIf  map 是什么类型
// 2. map init -> str 最终接受是什么类型
// 3. map per -> stu 最终接受的是什么类型
// 4. 验证是否是此类型 与 null
fun main () {
    val strResult = KTBase105(true, "Hello").map { it.length }
    println("String map result: $strResult")

    val stuResult = KTBase105(true, "Student").map { "Mapped $it" }
    println("Student map result: $stuResult")

    val nullResult = KTBase105(false, "Hidden").map { it.uppercase() }
    println("Null result (isMap=false): $nullResult")


    val p1 = KTBase105(true, 3443)
    val r1 = p1.map { it: Int ->
        it
        it.toString() // lambda 最后一行是返回值
    }
    println("r1 result: $r1")

//    val str1: String = "OK1"
    println("Is r1 a String?: ${r1 is String}")
    println("Is r1 a String? (nullable check): ${r1 is String?}")

    // 3.
    val p2 = KTBase105(true, Person3("王五", 78))
    val r2 : Student? = p2.map {
        // it == Person 对象 == inputType
        it
        Student(it.name, it.age)
    }
    println("r2 result: $r2")

}


