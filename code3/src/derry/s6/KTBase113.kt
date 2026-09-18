#!/usr/bin/env kotlin

package derry.s6

class KTBase113 (val  name: String, val age: Int)

// 增加扩展函数
fun KTBase113.show() {
    println("增加一个show函数")
}
// 增加扩展函数
fun KTBase113.getInfo() = "getInfo--name:$name,age:$age"

// 增加扩展函数的背后
/**
public final class KTBase113 {
    public static final void show(KTBase113 $this$show) {
        System.out.println("show函数，name:" + $this$show.name +",age" + $this$show.age)
    }

    public static final void getInfo(KTBase113 $this$getInfo) {
        System.out.println("getInfo函数，name:" + $this$getInfo.name +",age" + $this$getInfo.age)
    }

    public static void main(String [] args) {
        main()
    }

}
*/

// 定义扩展函数
fun main () {
//    val p = KTBase113("张三", 24)
//    p.show()
//    println(p.getInfo())
}


