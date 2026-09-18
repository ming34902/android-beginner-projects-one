package derry.s7;

public class SingletonDemo2 {
    // 双重
    private static volatile SingletonDemo2 instance;

    private SingletonDemo2() {}

    public static SingletonDemo2 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo2.class) {
                if (instance == null) {
                    instance = new SingletonDemo2();
                }
            }
        }
        return instance;
    }
}
