package top.lishuoboy.javase.designpatterns.singleton.eager;


import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.caller.CallerUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.lishuoboy.javase.designpatterns.singleton.lazy.*;

import java.util.Set;

/**
 * @author lishuoboy
 * @date 2022/5/15 12:57
 */
@Slf4j
public class SingletonTest {
    @Test
    public void testSingleton1() {
        Set<Singleton1> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton1.INSTANCE);
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }

    @Test
    public void testSingleton2() {
        Set<Singleton2> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton2.INSTANCE);
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
        Console.log(CallerUtil.getCallerMethodName(false) + " : Singleton2.INSTANCE=={}", Singleton2.INSTANCE.toString());
    }

    @Test
    public void testSingleton3() {
        Set<Singleton3> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton3.INSTANCE);
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }

    @Test
    public void testSingleton4() {
        Set<Singleton4> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton4.getInstance());
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }

    @Test
    public void testSingleton5() {
        Set<Singleton5> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton5.getInstance());
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }

    @Test
    public void testSingleton6() {
        Set<Singleton6> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton6.getInstance());
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }

    @Test
    public void testSingleton7() {
        Set<Singleton7> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton7.getInstance());
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }

    @Test
    public void testSingleton8() {
        Set<Singleton8> set = new ConcurrentHashSet<>();
        ThreadUtil.concurrencyTest(1000, () -> {
            set.add(Singleton8.getInstance());
        });
        Console.log(CallerUtil.getCallerMethodName(false) + " : size=={}", set.size());
    }
}