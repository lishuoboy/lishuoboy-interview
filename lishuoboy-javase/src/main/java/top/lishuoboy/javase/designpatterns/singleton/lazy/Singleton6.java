package top.lishuoboy.javase.designpatterns.singleton.lazy;

/** ⑥. 懒汉式：同步代码块，单检查，线程不安全 */
public class Singleton6 {
    /**
     * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
     * private，防止获取到null
     */
    private static Singleton6 INSTANCE;

    /** 1. 私有化构造器 */
    private Singleton6() {
    }

    /**
     * 2. 自己创建实例
     * 4. 向外提供这个实例
     */
    public static Singleton6 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton6.class) {
                INSTANCE = new Singleton6();
            }
        }
        return INSTANCE;
    }
}