package top.lishuoboy.javase.designpatterns.singleton.lazy;

/**
 * ⑧. 懒汉式：静态内部类
 * * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的。
 * * 因为是在内部类加载和初始化时，创建的，因此是线程安全的
 */
public class Singleton8 {
    /** 静态内部类 */
    private static class Inner {
        /**
         * 2. 自己创建实例
         * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
         */
        private static final Singleton8 INSTANCE = new Singleton8();
    }

    /** 1. 私有化构造器 */
    private Singleton8() {
    }

    /** 4. 向外提供这个实例 */
    public static Singleton8 getInstance() {
        return Inner.INSTANCE;
    }
}