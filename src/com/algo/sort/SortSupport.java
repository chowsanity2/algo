package com.algo.sort;

import java.lang.reflect.Proxy;

/**
 *  排序辅助
 *  @author chowsanity
 */
public class SortSupport {
    /**
     *  被代理对象
     */
    private final Object raw;

    public SortSupport(Object raw) {
        this.raw = raw;
    }

    public Object getPrinter(){
        return Proxy.newProxyInstance(raw.getClass().getClassLoader(), raw.getClass().getInterfaces(), SortPrintInvocationHandler.getInstance(raw));
    }
}
