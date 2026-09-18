#!/usr/bin/env kotlin

package derry.s5


class LimbsInfo (var limbsInfo: String, var length: Int) {
    fun show() {
        println("limbsInfo:$limbsInfo,length:$length")
    }

}

enum class Limbs(private val limbsInfo: LimbsInfo) {
    Left_HAND(LimbsInfo("左手", 55)),
    RIGHT_HAND(LimbsInfo("左手", 45)),
    Left_FOOT(LimbsInfo("左手", 89)),
    RIGHT_FOOT(LimbsInfo("左手", 88));

    // 1.week 这个时候 再定义单调的 枚举值，就会报错，必须所有枚举值，保持一致的效果
    // 2. 枚举的 主构造的参数 必须和 枚举的参数 保持一致

    fun show() = "limbsInfo:$limbsInfo,length:$limbsInfo.length"


    fun updateData(limbsInfo: LimbsInfo) {
        println("更新前的数据：${this.limbsInfo}")
        this.limbsInfo.limbsInfo = limbsInfo.limbsInfo
        this.limbsInfo.length = limbsInfo.length
        println("更新后的数据：${this.limbsInfo}")
    }
}

// 枚举定义函数
fun main () {
    /*
    一般不会这样调用
    *  println(Limbs.show())
    *  println(Limbs.show())
    * */

    println(Limbs.Left_HAND.show())

    // 更新
    Limbs.RIGHT_FOOT.updateData(LimbsInfo("右脚2", 67))

}


