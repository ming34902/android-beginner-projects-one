#!/usr/bin/env kotlin

package derry.s6


interface USB2 {
    // 1 接口 var也是不能给接口成员赋值的
    // 2. 任何类 接口等等， val代表只读的，是不可以后面动态赋值的

    val usbVersionInfo: String
        get() = (1..100).shuffled().last().toString()  // val 不需要set

    val usbInsertDevice: String
        get() = "高级设备接入USB"

    fun insertUSB(): String
}

class Mouse2(override var usbVersionInfo: String = "USB3.0", override var usbInsertDevice: String = "鼠标接入了USB"): USB2 {
    override fun insertUSB() = "Mouse2---usbVersionInfo$usbVersionInfo, usbInsertDevice:$usbInsertDevice"
}

class Mouse3: USB2 {
    override val usbVersionInfo: String
        get() = super.usbVersionInfo
    override val usbInsertDevice: String
        get() = super.usbInsertDevice

    override fun insertUSB() = "Mouse3---usbVersionInfo$usbVersionInfo, usbInsertDevice:$usbInsertDevice"
}

// 核心接口默认实现
fun main () {
    val usb1 = Mouse3()
    println(usb1.insertUSB())
}


