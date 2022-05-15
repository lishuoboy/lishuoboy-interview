package top.lishuoboy.javase.designpatterns.singleton.eager;

/** ①. 饿汉式：静态常量，直接创建实例 */
public class Singleton1 {
    /**
     * 2. 自己创建实例
     * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
     * 4. 向外提供这个实例：方式一
     */
    public static final Singleton1 INSTANCE = new Singleton1();

    /** 1. 私有化构造器 */
    private Singleton1() {
    }

    /** 4. 向外提供这个实例：方式二 */
    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}