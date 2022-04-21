package org.example.PerformanceProxy;

import org.example.CachingProxy.CachingHandler;
import org.example.Calculator.Metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class GetTimeHandler implements InvocationHandler {
    private final Object delegate;
    private final String str = "Execution time of method %s with args %s is %d nanoseconds";
    public GetTimeHandler(Object delegate) {
        this.delegate = delegate;
    }

    /**
     * Выводим на экран время выполнения метода в наноскундех
     * если он аннотирован Metric
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Metric.class)){
            long start = System.nanoTime();
            Object result = method.invoke(delegate, args);
            long finish = System.nanoTime() - start;
            System.out.println(String.format(str, method.getName(),Arrays.toString(args),finish));
            return result;
        }
        return method.invoke(delegate, args);
    }
}