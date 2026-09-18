#!/usr/bin/env kotlin

package derry.s6

interface IUSB {
    var usbVersionInfo: String // usb版本相关信息
    var usbInsertDevice: String // usb插入设备信息

    fun inserUSB(): String
}

class Mouse(override var usbVersionInfo: String = "USB 3.0", override var usbInsertDevice: String = "鼠标接入了USB口"): IUSB {
    override fun inserUSB(): String {
        return "usbVersionInfo: $usbVersionInfo, usbInsertDevice:$usbInsertDevice"
    }
}

// 键盘USB实现类
class KeyBoard: IUSB {
    override var usbVersionInfo: String = ""
        get() = field
        set(value) {
            field = value
        }
    override var usbInsertDevice: String = ""
        get() = field
        set(value) {
            field = value
        }
    override fun inserUSB(): String {
        return "KeyBoard---usbVersionInfo: $usbVersionInfo, usbInsertDevice:$usbInsertDevice"
    }
}

// 1，接口里面所有成员 和接口本身都是 public open的，所有不需要open,这个接口的特殊
// 2. 接口不能有主构造，反正就是没有构造
// 3. 实现类不仅仅要重写接口的函数，也要重写接口的成员
// 4. 接口实现代码区域，全部要增加 关键字来 override 修饰
fun main () {
    val iusb1 : IUSB = Mouse()
    println(iusb1.inserUSB())


    val iusb2 : IUSB = KeyBoard()
    println(iusb2.inserUSB())

    iusb2.usbVersionInfo = "AAA"
}


