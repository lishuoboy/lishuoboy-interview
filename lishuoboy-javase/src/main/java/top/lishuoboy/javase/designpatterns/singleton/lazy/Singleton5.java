package top.lishuoboy.javase.designpatterns.singleton.lazy;

/** ⑤. 懒汉式：同步方法 */
public class Singleton5 {
    /**
     * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
     * private，防止获取到null
     */
    private static Singleton5 INSTANCE;

    /** 1. 私有化构造器 */
    private Singleton5() {
    }

    /**
     * 2. 自己创建实例
     * 4. 向外提供这个实例
     */
    public static synchronized Singleton5 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton5();
        }
        return INSTANCE;
    }

    /** 4. 向外提供这个实例，与上面锁方法基本一样 */
/*    public static Singleton5 getInstance1() {
        synchronized (Singleton5.class) {
            if (INSTANCE == null) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }*/
}
