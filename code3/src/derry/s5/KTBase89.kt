#!/usr/bin/env kotlin

package derry.s5

class KTBase89 {
   // 伴生对象
   companion object {
      val info = "DerryInfo"
      fun showInfo() = println("显示$info")
      val name = "Derry"
   }
   /*
   * companion object {} 背后逻辑
   * private static final String info = "";
   * public static final KTBase89.Companion Companion = new KTBase89.Companion(xxx);
   * public static final class Companion {
   *     @NotNull
   *     public final String getInfo() {
   *        return KTBase89.info();
   *     }
   *     @NotNull
   *     public final String getName() {
   *        return KTBase89.name();
   *     }
   *
   *     public final void showInfo() {
   *        String var1 = "显示：" + ((KTBase89.Companion)this).getInfo()
   *        boolean var2 = false;
   *        System.out.prinLn(var1);
   *     }
   *
   *     private Companion() {}
   *
   *     // synthetic method
   *     public Companion(DefaultConstructorMarker $constructor_marker) {
   *        this();
   *     }
   * }
   * */
}

//  伴生对象
// 伴生对象的由来。 在kotlin 中没有java这种static 静态，伴生很大成都上和java这种static静态差不多
// 无论 KTBase89() 构造对象多少次，我们的伴生对象，只有一次夹杂
// 无论 KTBase89().showInfo() 调用多少次，伴生对象，只有一次加载
// 伴生对象只会初始化一次
fun main () {
   // System.out.println(KTBase89.Companion.getInfo())
   println(KTBase89.info)

   // KTBase89.Companion.showInfo()
   KTBase89.showInfo()

   // new KTBase89()
   KTBase89()
}


