package org.example;

import org.example.Calculator.Calculator;
import org.example.Calculator.CalculatorImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.example.ReflectionTestClass.*;

/**
 * Home Work
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Calculator calculator = new CalculatorImpl();
        //getAllAndSuperMethodsAndPrint(calculator);
        List<Method> gettersMethods = getAllGettersMethods(calculator);
        gettersMethods.forEach(getter -> System.out.println(getter.getName()));
        List<Field> listFields = getFinalStringsFields(calculator);
        listFields.forEach(x -> System.out.println(x.getName()));

    }
}
