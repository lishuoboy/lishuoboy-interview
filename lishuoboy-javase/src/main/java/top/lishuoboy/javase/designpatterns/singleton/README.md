- ### CSND博客 [单例模式的8种写法](https://editor.csdn.net/md/?articleId=124787029)
- ### 码云源码  [lishuoboy-interview](https://gitee.com/lishuoboy/lishuoboy-interview/tree/master/lishuoboy-javase/src/main/java/top/lishuoboy/javase/designpatterns/singleton)

# 1. 单例模式介绍

- 单例模式是一种常用的软件设计模式，其定义是单例对象的类只能允许一个实例存在，只能new 对象一次。
- 许多时候整个系统只需要拥有一个的全局对象，这样有利于我们协调系统整体的行为。比如在某个服务器程序中，该服务器的配置信息存放在一个文件中，这些配置数据由一个单例对象统一读取，然后服务进程中的其他对象再通过这个单例对象获取这些配置信息。这种方式简化了在复杂环境下的配置管理。

- 适用场合

1. 需要频繁的进行创建和销毁的对象；
2. 创建对象时耗时过多或耗费资源过多，但又经常用到的对象；
3. 工具类对象；
4. 频繁访问数据库或文件的对象。

# 2. 代码实现要点

1. 私有化构造器（防止在其他类new对象造成多实例）
2. 自己创建实例
3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
4. 向外提供这个实例（用public属性或 public static get方法对外提供，通常使用getInstance这个名称）

# 3. 七种实现代码　

## 3.1. 饿汉式

- 饿汉式（eager）。很饿很饥渴，需要立即吃饭（立即创建实例）
- 优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
- 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费。

### ①. 饿汉式：静态常量，直接创建实例

```java
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
```

### ②. 饿汉式：静态代码块，创建实例

- 这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。优缺点和上面是一样的。适合复杂实例化

```java
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
```

singleton.properties 文件

```properties
info=尚硅谷
```

### ③. 饿汉式：枚举（天然线程安全）

- 借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。可能是因为枚举在JDK1.5中才添加，所以在实际项目开发中，很少见人这么写过。

```java
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
```

## 3.2. 懒汉式

- 懒汉式（lazy），很懒，不到用的时候不干活（不实例化）

### ④. 懒汉式：无同步锁。线程不安全

- 这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。如果在多线程下，一个线程进入了if (INSTANCE == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式。

```java
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
```

### ⑤. 懒汉式：同步方法

- 解决上面第④种实现方式的线程不安全问题，最简单的解决方式是做个线程同步就可以了，于是就对getInstance()方法进行了线程同步。
- 优点：实现简单
- 缺点：效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接return就行了。方法进行同步效率太低要改进。

```java
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
```

### ⑥. 懒汉式：同步代码块，单检查，线程不安全

- 由于第⑤种实现方式同步效率太低，所以摒弃同步方法，改为同步代码块实例化。但是这种同步并不能起到线程同步的作用。跟第④种实现方式遇到的情形一致，假如一个线程进入了if (INSTANCE == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。

```java
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
```

### ⑦. 懒汉式：同步代码块，双重检查

- Double-Check概念对于多线程开发者来说不会陌生，如代码中所示，我们进行了两次if (INSTANCE == null)检查，这样就可以保证线程安全了。这样，实例化代码只用执行一次，后面再次访问时，判断if (INSTANCE == null)，直接return实例化对象。
- 优点：线程安全；延迟加载；效率较高。

```java
/** ⑦. 懒汉式：同步代码块，双重检查 */
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
                if (INSTANCE == null) {
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
```

### ⑧. 懒汉式：静态内部类

-

这种方式跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程。不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。

- 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
- 优点：避免了线程不安全，延迟加载，效率高。

```java
/**
 * ⑧. 懒汉式：静态内部类
 * * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的。
 * * 因为是在内部类加载和初始化时，创建的，因此是线程安全的
 */
public class Singleton7 {
    /** 静态内部类 */
    private static class Inner {
        /**
         * 2. 自己创建实例
         * 3. 静态变量保存（强调这是一个单例，我们可以用 final 修饰）
         */
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    /** 1. 私有化构造器 */
    private Singleton7() {
    }

    /** 4. 向外提供这个实例 */
    public static Singleton7 getInstance() {
        return Inner.INSTANCE;
    }
}
```

# 4.总结对比

| 序号 | 单例类别 | 线程安全 | 推荐性 | 其它特点 | |--|--|--|--|--| | 1 | <p align="left"> ①. 饿汉式：静态常量，直接创建实例 </p> | 安全 | 推荐 | 简洁直观 | | 2 | <p align="left"> ②. 饿汉式：静态代码块，创建实例 </p>| 安全 | 推荐 | 适合复杂实例化 | | 3
| <p align="left"> ③. 饿汉式：枚举（天然线程安全） </p>| 安全 | 推荐 | 最简洁，java1.5之后才有 | | 4 | <p align="left"> ④. 懒汉式：无同步锁。线程不安全 </p>| 不安全 | 不可用 | | | 5 | <p align="left"> ⑤. 懒汉式：同步方法 </p>| 安全 | 不推荐，性能低 | 安全性能低 | | 6
| <p align="left"> ⑥. 懒汉式：同步代码块，单检查，线程不安全 </p> | 不安全 | 不可用 | | | 7 | <p align="left"> ⑦. 懒汉式：同步代码块，双重检查 </p>|安全 | 推荐 | 安全性能高 | | 8 | <p align="left"> ⑧. 懒汉式：静态内部类 </p>|安全 | 推荐 | 安全性能高，不直观 |
