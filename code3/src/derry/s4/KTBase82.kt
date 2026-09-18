#!/usr/bin/env kotlin

package derry.s4

class KTBase82 (_info: String) {
    private val info = _info

    val content: String = getInfoMethod()

//    private val info = _info // 这种 info转换代码，要写在最前面

    private fun getInfoMethod() = info // 此时调用info变量的时候，以为赋值好了，其实还未赋值

}

// 1. 主构造 _info_ 放后面
// 2. value = initInfoAction() 放前面
// 3. p.value.length
fun main () {
    println("内容长度：${KTBase82("Derry").content.length}")
}


