#!/usr/bin/env kotlin

package derry.s4


// 可变 Map 集合
fun main () {
   val mMap2: MutableMap<String, Int> = mutableMapOf<String, Int>(Pair("sss", 545), Pair("Kevin", 324), "Leo" to 334)

    mMap2 += "AAAA" to 922
    mMap2["Leo"] = 77
    mMap2.put("assasa", 89)

    // getOrPut 如果没有 “Derry” 的情况下 ，就添加 key  “Derry” 到集合中，再从集合获取 返回555
    mMap2.getOrPut("Derry") { 555}

    mMap2.getOrPut("sss") { 555} // 555是备用值，有 “sss” 这个key，则备用返回失效
}


