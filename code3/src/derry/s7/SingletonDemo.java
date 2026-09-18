package derry.s7;

// 饿汉式实现
public class SingletonDemo {

    private SingletonDemo() {}

    private static class SingletonHolder {
        private static final SingletonDemo INSTANCE = new SingletonDemo();
    }

    public static SingletonDemo getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
