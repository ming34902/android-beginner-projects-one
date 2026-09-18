#!/usr/bin/env kotlin

package derry.s4


// Map 的创建
fun main () {
    val mMap1: Map<String, Double> = mapOf<String, Double>("Derry" to(545.4), "Kevin" to 3434.2)
    val mMap2 = mapOf(Pair("Derry", 545.4), Pair("Kevin", 324))
    println(mMap1["Derry"])
}


