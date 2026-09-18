#!/usr/bin/env kotlin

package derry.s4


class KTBase71 {
    val number1: Int = 0

    val number2: Int
        get() {
            TODO()
        }
    //  get() = (1..100).shuffled().first() // 1-100取随机值，返回给 getNumber2()函数
/**
 * 为什么没有看到number2的属性定义？
 *
 * 因为属于 计算属性 的功能，根本在getNumber2函数里，没有用到number2属性，所以
 *  public int gerNumber2() {
 *      return (1...1000).shuffled().first() java的随机逻辑复杂
 *  }
 * */
    var info1:String ? = /* Null*/ ""
    // 防范竞态条件  当你调用成员，这个成员可能为null,可能为空值，就必须采用 防范竞态条件，这个KT变成的规范化
    fun getShowInfo1() : String{
        // 这个成员可能是nul 可能是空值，就启用防范竞态条件
        return info1?.let {
            if (it.isBlank()) {
                "info是空值，请检查代码.."
            } else {
                "最终info的结果是：$it"
            }
        } ?: "info是null,请检查代码"
    }



}

//  计算属性 和 防范竞态条件
fun main () {
    // 背后隐式代码  System.out.println(new KtBase71().getNumber1())
    println(KTBase71().number1)
    // 背后隐式代码  new KtBase71().setNumber1(9)
    // KtBase71().number1 = 9 // val 没有 setXXX 函数，只有 getXXXX 函数


    // 背后隐式代码  System.out.println(new KtBase71().getNumber2())
    println(KTBase71().number2)
}


