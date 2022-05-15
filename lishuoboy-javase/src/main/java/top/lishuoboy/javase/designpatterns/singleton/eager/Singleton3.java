package top.lishuoboy.javase.designpatterns.singleton.eager;

/**
 * ③. 饿汉式：枚举（天然线程安全）
 * 枚举类型：表示该类型的对象是有限的几个。我们可以限定为一个，就成了单例
 */
public enum Singleton3 {
    /**
     * 1. 私有化构造器
     * 2. 自己创建实例
     * 4. 向外提供这个实例：方式一
     */
    INSTANCE;

    /** 4. 向外提供这个实例：方式二 */
    public static Singleton3 getInstance() {
        return INSTANCE;
    }
}