package org.example.PerformanceProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class GetTimeHandler implements InvocationHandler {
    private final String str = "Execution time of method %s with args %s is %d nanoseconds";
    private final Object object;
    public GetTimeHandler(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*if(method.isAnnotationPresent(Metric.class)){
            long start = System.nanoTime();
            Object result = method.invoke(proxy, args);
            long executionTime = System.nanoTime() - start;
            System.out.println("xxx");
            return result;
        }*/
        Object result = method.invoke(proxy, args);
        return result;
    }
}
