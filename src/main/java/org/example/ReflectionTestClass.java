package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionTestClass {
    /**
     * выводит на экран все методы класса, включая все родительские методы (включая приватные)
     * @param o - объект, с которым работаем
     */
    public static List<Method> getAllAndSuperMethodsAndPrint (Object o){
        List<Method> methodList = new ArrayList<>();
        Class claz = o.getClass();
        do{
            methodList.addAll(Arrays.asList(claz.getDeclaredMethods()));
            claz = claz.getSuperclass();
        } while (claz != null);
        return methodList;
    }

    /**
     * выводит на экран все геттеры класса объекта
     * @param o - объект
     */
    public static List<Method> getAllGettersMethods(Object o){
        List<Method> methodList = new ArrayList<>(Arrays.asList(o.getClass().getDeclaredMethods()));
        List<Method> gettersList = methodList.stream().filter(method -> method.getName().startsWith("get")).collect(Collectors.toList());
        return gettersList;
    }
    /**
     * получает все Final String поля класса объекта если их значение = их имени
     * @return список полей, прошедших проверку
     * @param o - объект
     */
    public static List<Field> getFinalStringsFields(Object o){
        List<Field> fieldList = new ArrayList<>();
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if(isModifierFinal(field.getModifiers()) && (field.get(o).getClass() == String.class) && ((field.get(o)).equals(field.getName()))) {
                    fieldList.add(field);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldList;
    }

    /**
     * Проверка на наличие модификатора final
     * @param modifier
     * @return true если модификатор final
     */
    public static boolean isModifierFinal(int modifier) {
        return (modifier & Modifier.FINAL) > 0;
    }
}
