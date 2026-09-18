#!/usr/bin/env kotlin

package derry.s4


class KTBase70 {
   /*
   * @NotNull
   * private String name = "Derry"
   * public void setName(@NotNull String name) {
   *    this.name = name;
   * }
   *
   * @NotNull
   * public String getName() {
   *    return this.name
   * }
   * */
   var name = "Derry"
      get() = field
      set(value) {
         field = "**[$value]**"
      }

   var info1 = "ABCDEFGHIJKMNOPQRSTUVWXYZ"

   // 下面是隐式代码，不写也有
   //   get() = field.capitalize() // 首字母改大写
   //   set(Value) {
   //      field = "**[$value]**"
   //   }
   /**
    * @NotNull
    * private String info = "ABCDEFGHIJKMNOPQRSTUVWXYZ"
    * public void setInfo ( @NotNull String info ) {
    *    this.info = "**[" + info + "]**"
    * }
    * @NotNull
    * public String getInfo() {
    * return StringKT.capitalize(this.info)
    * }
    * */
}

// 定义类和 field 关键字
fun main () {
    // 背后隐式代码： new ktBase70().setName("Kevin");
   KTBase70().name = "Enzo"
   // 背后隐式代码 System.out.println(new ktBase70().getName())
   println(KTBase70().name)


   // 背后隐式代码： System.out.println(new ktBase70().getInfo())
   println(KTBase70().info1)
   // new ktBase70().setInfo("xxxx");
   KTBase70().name = "xxxx"
}


