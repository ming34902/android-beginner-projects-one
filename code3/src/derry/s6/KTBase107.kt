#!/usr/bin/env kotlin

package derry.s6

// 为什么it 是String ?,因为 lambda (T ?) -> O  T? 指定了 ?
class KTBase107 <T> (vararg objects: T, var isMap: Boolean) {
    // out T只能被读取，不能修改
    private val objectArray : Array<out T> = objects

    // showObj(index)   index下标的对象可能是null
    fun showObj(index: Int) : T? = objectArray[index].takeIf { isMap } ?: null

    //    mapObj(index, 变换lambda)
//    fun <O> mapObj(index: Int, mapAction: (T?) -> O) = mapAction(objectArray[index].takeIf { isMap })
    fun <O> mapObj(index: Int, mapAction: (T) -> O) = mapAction(objectArray[index])

}

// vararg 关键字
// 1. objectArray: Array<T>
// 2. showObj(index)
// 3. mapObj(index, 变换lambda)
// 4. p.showObj  p.mapObj(int -> str)
// 5. p的类型 int类型
fun main () {
    // * java ?
    // 泛型， KTBase107<{ Comparable<* & java.io.Serializable }>
    // 由于不允许我们这样写 ： KtBase107<{ Comparable<* & java.io.Serializable }> 所以用父类Any? 代替
    val p: KTBase107<Any?> = KTBase107("Derry", false, 43432, 443.4f, 5454.3f, null, 'C', isMap = true)

    p.showObj(5)  // 特殊操作，如果是null,会引崩溃

    // mapObj
    // it 类型 实际上  { Comparable<* & java.io.Serializable } 需要转换一下类型，例如  it.toString()
    p.mapObj(0) {
        it
        it.toString()
    }

    // 第三个元素是 int ，不需要转换
    val r2 : String = p.mapObj(2) {
        "第三个元素是：$it"
    }



}


