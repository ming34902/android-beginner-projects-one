package derry.s7;

public class KtBase133xx {
    public static void main(String[] args) {
        // java
        // ktBase133.show("张三") // show--java无法使用 Kotlin默认参数
        KTBase133Kt.toast("住宅"); // JvmOverloads 生成重载函数，所以 toast  使用kotlin默认参数
    }
}
