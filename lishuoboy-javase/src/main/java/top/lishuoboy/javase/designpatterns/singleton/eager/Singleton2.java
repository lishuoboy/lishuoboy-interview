package top.lishuoboy.javase.designpatterns.singleton.eager;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;
import lombok.ToString;

/**
 * ②. 饿汉式：静态代码块，创建实例
 * 饿汉式：适合复杂实例化，如加载配置文件数据
 */
@ToString
public class Singleton2 {
    /**
     * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
     * 4. 向外提供这个实例：方式一
     */
    public static final Singleton2 INSTANCE;

    private String info;

    /** 2. 自己创建实例 */
    static {
        Props props = PropsUtil.get("top.lishuoboy/javase.designpatterns.singleton/singleton.properties");
        String info = (String) props.get("info");
        INSTANCE = new Singleton2(info);
    }

    /** 1. 私有化构造器 */
    private Singleton2(String info) {
        this.info = info;
    }

    /** 4. 向外提供这个实例：方式二 */
    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}