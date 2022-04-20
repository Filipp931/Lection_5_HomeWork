package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class BeansUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Map<String, Method> fromPublicGetterMap = getPublicGettersMap(from);
        Map<String, Field> toPublicSetterFieldsMap = getPublicSettersFieldsMap(to);
        fromPublicGetterMap.keySet().forEach(field -> {
            if(toPublicSetterFieldsMap.containsKey(field)){
                try {
                    if (isCompatible(fromPublicGetterMap.get(field), field, to)) {
                        Field temp = toPublicSetterFieldsMap.get(field);
                        temp.setAccessible(true);
                        temp.set(to, fromPublicGetterMap.get(field).invoke(from));
                    }
                }  catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Построение карты имени переменной, у которой public getter
     * @param obj
     * @return Map<K,V> K - имя поля, которому соответствует public getter, V - сам getter
     */
    public static Map<String, Method> getPublicGettersMap(Object obj){
        Map<String, Method> publicGettersMap = new HashMap<>();
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if(method.getName().startsWith("get") && isModifierPublic(method.getModifiers())) {
                StringBuilder name = new StringBuilder(method.getName().replace("get",""));
                name.setCharAt(0, Character.toLowerCase(name.charAt(0)));
                publicGettersMap.put(name.toString(), method);
            }
        }
        return publicGettersMap;
    }

    /**
     * Построение карты имени переменной, у которой public setter
     * @param obj
     * @return Map<K,V>   K - имя поля, которому соответствует public setter, V - сам setter
     */
    public static Map<String, Field> getPublicSettersFieldsMap(Object obj){
        Map<String, Field> publicSettersFieldsMap = new HashMap<>();
        List<Method> publicSetters = new ArrayList<>();
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if(method.getName().startsWith("set") && isModifierPublic(method.getModifiers())) {
                publicSetters.add(method);
            }
        }
        publicSetters.forEach(setter -> {
            StringBuilder name = new StringBuilder(setter.getName().replace("set", ""));
            name.setCharAt(0, Character.toLowerCase(name.charAt(0)));
            try {
                Field field = obj.getClass().getDeclaredField(name.toString());
                publicSettersFieldsMap.put(name.toString(), field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();

            }
        });
        return publicSettersFieldsMap;
    }

    /**
     * Провка соответствия возвращаемого типа gettera типу переменной с названием fieldToName объекта to
     * @param getterFrom - метод, возвращаемый тип которого проверяется на совместимость с to.fieldToName
     * @param fieldToName - имя поля объекта, которое проверяем на совместимость
     * @param to - объект класса, поле которого проверяем на совместимость
     * @return true если getter() возвращает тип instance of to.fieldTo
     * @throws NoSuchFieldException если поле с названием fieldToName не найдено
     */
    public static boolean isCompatible(Method getterFrom, String fieldToName, Object to) throws NoSuchFieldException {
        Class fromClass = getCorrectClassType(getterFrom.getReturnType());
        Class toClass = getCorrectClassType(to.getClass().getDeclaredField(fieldToName).getType());
        return toClass.isAssignableFrom(fromClass);
    }

    /**
     * Получение класса обертки в случае примитива
     * @param clazz
     * @return класс-обертку, в случае примитива, либо clazz
     */
    public static Class getCorrectClassType(Class clazz){
        Map<Class, Class> classes = new HashMap<>();
        classes.put(int.class, Integer.class);
        classes.put(boolean.class, Boolean.class);
        classes.put(byte.class, Byte.class);
        classes.put(char.class, Character.class);
        classes.put(float.class, Float.class);
        classes.put(long.class, Long.class);
        classes.put(short.class, Short.class);
        return classes.containsKey(clazz) ? classes.get(clazz) : clazz;
    }

    /**
     * проверка модификатора доступа
     * @param modifier Int
     * @return true если моификатор final
     */
    public static boolean isModifierPublic(int modifier) {
        return ((modifier & Modifier.PUBLIC) > 0);
    }

}
