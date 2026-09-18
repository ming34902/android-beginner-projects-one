#!/usr/bin/env kotlin

package derry.s5

data class KTBase92 (var name: String, var age: Int) { // 主构造

   var coreInfo :String = ""
   init {
       println("主构造调用")
   }

   // 次构造
   constructor(name: String) : this(name, 99) {
      println("次构造调用")
      coreInfo = "增加非核心的内容信息"
   }

   override fun toString(): String {
//      return super.toString()
      return "toString name: $name, age:$age，coreInfo:$coreInfo"
   }
}

/**
 * 生成 toString 为什么只有两个参数
 * public String toString() {
 *    return "KTBase92(name = "+this.name + ",age=" + this.age+ ")";
 * }
 * */

// copy函数
fun main () {
   val p1 = KTBase92("张三")

   val p2 = p1.copy("王五", 78)
   println(p2)

   // copy toString hashCode equals 等等 主管主构造，不管次构造
   // 注意事项： 使用copy睡觉哦就。由于内部代码只处理主构造，所以必须考虑次构造的内容
}


