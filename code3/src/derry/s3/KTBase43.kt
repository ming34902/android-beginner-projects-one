#!/usr/bin/env kotlin

package derry.s3

const val INFO1 = "Derry is result"
//  substring
fun main () {

    val indexOf = INFO1.indexOf("i")
    println(INFO1.substring(0, indexOf))
    println(INFO1.substring(0 until indexOf))
}

