#!/usr/bin/env kotlin

package derry.s5

// 在kotlin在，所有的类，都隐式基础： Any() 不写，也默认就有
class obj1 : Any()

//  Any超类，  Any == java Object
fun main () {
   println(obj1().toString())
}


