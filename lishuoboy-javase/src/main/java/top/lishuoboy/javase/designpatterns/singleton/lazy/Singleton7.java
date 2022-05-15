package top.lishuoboy.javase.designpatterns.singleton.lazy;

/** ⑦. 懒汉式：同步代码块，双重检查 */
public class Singleton7 {
    /**
     * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
     * private，防止获取到null
     */
    private static Singleton7 INSTANCE;

    /** 1. 私有化构造器 */
    private Singleton7() {
    }

    /**
     * 2. 自己创建实例
     * 4. 向外提供这个实例
     */
    public static Singleton7 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton7.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton7();
                }
            }
        }
        return INSTANCE;
    }
}