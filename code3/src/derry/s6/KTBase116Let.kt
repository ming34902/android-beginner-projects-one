#!/usr/bin/env kotlin

package derry.s6

// private 私有化
// inline 函数是高价函数，内联，做lambda优化，性能提高
// fun<I,O> 在函数中，声明两个泛型，I输入 Input ，O输出 Output
// I.mLet 对I输入Input进行函数扩展，扩展函数的名称是 mLet ，意味着所有类型，万能类型都能用xxx.mLet
// lambda:(I)-> O (I输入) -> O输出
// :O 会根据用户返回类型，变化而变化
// lambda(this) I进行函数扩展，在整个扩展函数里面 this == I本身
private inline fun<I, O> I.mLet(lambda: (I) -> O) = lambda(this)

// 标准函数与泛型扩展函数
fun main () {
    val r: String = "Derry".mLet {
        it
        true
        "Ok"
    }

    val r2: String = "Derry2".let {
        it
        34434.4f
        "Derry"
    }
}


