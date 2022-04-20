package org.example.CachingProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingHandler implements InvocationHandler {
    private final Object delegate;
    private Сache сache;
    /**
     * Хранилище кэша
     * @param <T>
     */
    private class Сache<T> {
        private Map<Method, HashMap<Object[],T>> cache =  new HashMap();
        /**
         * Проверка наличия кэшированного значения для метода method(parameter)
         * @param method
         * @param parameter
         * @return true - если значение было сохранено ранее
         */
        public boolean containsCachedValue(Method method, Object[] parameter){
            Boolean contains = false;
            if(!cache.containsKey(method)) return false;
            for (Object[] param : cache.get(method).keySet()) {
                if(Arrays.equals(param, parameter))
                    contains = true;
            }
            return contains;
        }

        /**
         * Получение кэшированного результата выполнения метода method(parameter)
         * @param method
         * @param parameter
         * @return значение метода method(parameter) из кэша
         */
        public T getCachedValue(Method method, Object[] parameter) {
            if(!containsCachedValue(method,parameter)) return null;
            Map<Object[],T> temp = cache.get(method);
            for (Object[] param : temp.keySet()) {
                if(Arrays.equals(param, parameter))
                    return temp.get(param);
            }
            return null;
        }

        /**
         * Кэширование результирующего значения метода value = method(parameter)
         * @param method
         * @param parameter
         * @param value
         */
        public void cachValue(Method method, Object[] parameter, T value ){
            HashMap<Object[], T> temp = new HashMap<>();
            temp.put(parameter, value);
            cache.put(method, temp);
        }
    }

    public CachingHandler(Object delegate) {
        this.delegate = delegate;
        сache = new Сache();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(сache.containsCachedValue(method, args) ) {
            Object result = сache.getCachedValue(method, args);
            System.out.println("Getting cash value from cash " + method.toString() +" = " + result);
            return result;
        } else {
            Object result = method.invoke(delegate, args);
            сache.cachValue(method, args, result);
            return result;
        }
    }

}
