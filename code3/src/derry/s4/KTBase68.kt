#!/usr/bin/env kotlin

package derry.s4


// 读取 Map 的遍历
fun main () {
   val mMap1: Map<String, Int> = mapOf<String, Int>(Pair("Derry", 545), Pair("Kevin", 324), "Leo" to 334)

//   mMap1.forEach { string, i ->  }
    mMap1.forEach {
       // it 内容 每一个元素 （K 和 V）
       println("k:${it.key},v:${it.value}")
    }


    mMap1.forEach { key:  String, value: Int ->
       println("k:${key},v:${value}")
    }

    mMap1.forEach { (k, v) ->
       println("k:${k},v:${v}")
    }

    for(item: Map.Entry<String, Int> in mMap1) {
        println("k:${item.key},v:${item.value}")
    }
}


