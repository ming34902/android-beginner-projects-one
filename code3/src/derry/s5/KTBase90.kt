#!/usr/bin/env kotlin

package derry.s5

class Body (_bodyInfo: String) {
   val bodyInfo = _bodyInfo

   // 默认情况下 内部类不能访问外部类，要增加修饰符inner 成为内部类，才能访问外部类
   inner class Heart {
      fun run() = println("Heart is beating for: $bodyInfo")
   }

   inner class Kidney {
      fun work() = println("Kidney is beating for: $bodyInfo")
   }

   inner class Hand {
      inner class LeftHand {
         fun run() = println("LeftHand is beating for: $bodyInfo")
      }
      inner class RightHand {
         fun run() = println("RightHand is beating for: $bodyInfo")
      }
   }
}
class  Outer {
   val info: String = "ok"
   fun show() {
      Nested().output()
   }
   class Nested {
      fun output() = println()
   }
}

// 嵌套类
fun main () {
   // 内部类
   val body = Body("Human Body")
   val heart = body.Heart()
   heart.run()

   Body("is ok").Hand().LeftHand().run()

   // 嵌套类
   Outer.Nested().output()
}


