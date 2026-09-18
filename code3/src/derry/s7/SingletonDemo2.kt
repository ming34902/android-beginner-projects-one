package derry.s7

class SingletonDemo2xx {
    companion object {
        @Volatile
        private var instance: SingletonDemo2xx? = null

        fun getInstance(): SingletonDemo2xx {
            return instance ?: synchronized(this) {
                instance ?: SingletonDemo2xx().also { instance = it }
            }
        }
    }
    fun show() = println("show")
}

fun main() {
    SingletonDemo2xx.getInstance().show()
}

//        val instance: SingletonDemo2xx by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            SingletonDemo2xx()
//        }