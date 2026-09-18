#!/usr/bin/env kotlin

package derry.s6


open class MyAnyClass(name: String) // 祖宗类
open class PersonClass(name: String) : MyAnyClass(name = name) // 父类

class StudentClass(name: String) : PersonClass(name = name)
class TeacherClass(name: String) : PersonClass(name = name)

class DogClass(name: String)

// T : PersonClass 相当于 java的 T extends PersonClass
// PersonClass本身 与 PersonClass的所有子类 都可以使用，其它的类，都不能兼容此泛型
class KTBase106<T> (private val inputValue: T, private val isR: Boolean = true) {
    fun getObj() = inputValue.takeIf { isR }
}

//  泛型类型约束
fun main () {
    val k1 = MyAnyClass("Derry1")
    val p1 = PersonClass("Derry1")
    val s1 = StudentClass("Derry1")
    val t1 = TeacherClass("Derry1")
    val d1 = DogClass("Derry1")

//    val r1 = KTBase106(k1).getObj()

    val r2 = KTBase106(p1).getObj()
    val r3 = KTBase106(s1).getObj()
    val r4 = KTBase106(t1).getObj()

//    val r5 = KTBase106(d1).getObj()

}


