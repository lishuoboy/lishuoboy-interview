package top.lishuoboy.javase.designpatterns.singleton.lazy;

/** ④. 懒汉式：无同步锁。线程不安全 */
public class Singleton4 {
    /**
     * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
     * private，防止获取到null
     */
    private static Singleton4 INSTANCE;

    /** 1. 私有化构造器 */
    private Singleton4() {
    }

    /**
     * 2. 自己创建实例
     * 4. 向外提供这个实例
     */
    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}