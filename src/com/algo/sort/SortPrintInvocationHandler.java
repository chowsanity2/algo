package com.algo.sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author admin
 */
public class SortPrintInvocationHandler implements InvocationHandler {
    private static final String SUFFIX = "SORT";

    private Object raw;

    public void setRaw(Object raw) {
        this.raw = raw;
    }

    private SortPrintInvocationHandler() {

    }

    public static InvocationHandler getInstance(Object raw){
        SortPrintInvocationHandler handler = SortHandlerFactory.INSTANCE;
        handler.setRaw(raw);
        return handler;
    }

    public static class SortHandlerFactory{
        private static final SortPrintInvocationHandler INSTANCE = new SortPrintInvocationHandler();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if (args.length == 1 && name.toUpperCase().endsWith(SUFFIX)) {
            long start = System.currentTimeMillis();
            Object result = method.invoke(raw, args);
            long end = System.currentTimeMillis();
            stdPrint(name, end - start);
//            if(args[0] instanceof int []){
//                System.out.println(Arrays.toString((int[]) args[0]));
//            }
            return result;
        } else {
            return method.invoke(raw, args);
        }
    }

    private void stdPrint(String methodName, long time) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < methodName.length(); i++) {
            char ch = methodName.charAt(i);
            prefix.append(Character.isUpperCase(ch) ? " " + Character.toLowerCase(ch)
                    : ch);
        }
        System.out.println(prefix + " cost: " + time + "ms");
    }
}
