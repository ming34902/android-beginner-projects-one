#!/usr/bin/env kotlin

package derry.s4

import java.io.File


// 数组类型
/*
   java        kotlin
* IntArray     intArrayOf
* DoubleArray    doubleArrayOf
* LongArray   longArrayOf
* ShortArray  shortArrayOf
* BooleanArray booleanArrayOf
* FloatArray   floatArrayOf
  Array    arayOf
*/
// 1.intArrayOf 常规操作的 越界崩溃
// 2.elementAtOrElse  elementAtOrNull
// 3.List 集合转 数组
// 4.arayOf  Array<File>
fun main () {

    // intArrayOf  常规操作的 越界崩溃
    val intArray = intArrayOf(1,2,44,5)

    println(intArray[0])

    println(intArray.elementAtOrElse(0) { -1 })
    println(intArray.elementAtOrElse(100) { -1 })


    println(intArray.elementAtOrNull(0) ?: -1)
    println(intArray.elementAtOrNull(100) ?: -1)

    val charArray1 = listOf('a', 'b', 'c').toCharArray()
    println(charArray1)


    val ObjArray = arrayOf(File("AAAA"), File("BBB"), File("SSSS"))

}


