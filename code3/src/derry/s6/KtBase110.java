package derry.s6;

import java.util.ArrayList;
import java.util.List;

public class KtBase110 {

    public static void main(String[] args) {
        List<CharSequence> list1 = new ArrayList<>();

        //   默认情况下： 泛型的父类 是不可以赋值给  泛型的子类

        // CharSequence 父类 String子类
        // ? extends T 等价于 Kotlin里的 out，所以  泛型的子类 是不可以赋值给  泛型的父类
        List<? extends CharSequence> list2 = new ArrayList<String>();

        // 协变： 父类 泛型声明处 可接收  子类 泛型具体处
    }
}
