#!/usr/bin/env kotlin

package derry.s4


// 读取 Map 的值
// 1. [] 找不到会返回 null
// 2. getOrDefault
// 3. getOrElse
// 4. 与java一样 会崩溃
fun main () {
   val mMap = mapOf("Derry" to 545, "Kevin" to 3434)

    println(mMap["Derry"])
    println(mMap.get("Derry"))
    println(mMap["xxx"]) // map通过key找，如果找不到返回null,不会崩溃

    println(mMap.getOrDefault("Derry", -1))
    println(mMap.getOrDefault("Derry2", -1))

    println(mMap.getOrElse("Derry") { -1 })
    println(mMap.getOrElse("Derry2") { -1 })


    // getValue 和 java的一样 会崩溃，尽量不要使用此方式
    println(mMap.getValue("Derry"))
    println(mMap.getValue("Derry2"))
}


