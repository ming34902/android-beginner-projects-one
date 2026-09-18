#!/usr/bin/env kotlin

package derry.s5

object KTBase87 {
   /*
   * object 对象类背后做了什么
   * public static final KTBase87 INSTANCE;
   *
   * private KTBase87() {} // 主构造废除一样的效果
   *
   * public final void show() {
   *   String var1 = "我是show函数。。。"
   *     System.out.println(var1)
   * }
   *
   * */
   init {
       println("KTBase87 init")
   }

   fun show() = println("show函数")

}

//  对象声明
fun main () {
   // KTBase87 既是单例实例，又是类名，只有一个创建，这就是典型的单例
   println(KTBase87) // 背后代码：  println(KTBase87.INSTANCE)
}


