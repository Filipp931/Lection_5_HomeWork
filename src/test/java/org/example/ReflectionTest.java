package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.TestClasses.From;
import org.example.TestClasses.To;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Unit test for ReflectionTestClass.
 */
public class ReflectionTest
{
    public final String testString = "testString";

    public String getTestString() {
        return testString;
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void assignTest(){
    From from = new From(213,654,90,"From");
        System.out.println("FROM"+from.toString());
    To to = new To(564, 2,4,false);
        System.out.println( " TO " + to.toString());
    BeansUtils.assign(to,from);
        System.out.println("FROM"+from.toString());
        System.out.println( " TO " + to.toString());
}




    @Test
    public void CalculatorFactorialTest()
    {
        Calculator calculator = new CalculatorImpl();
        Integer f0 = calculator.calc(0);
        Integer f1 = calculator.calc(1);
        Integer f5 = calculator.calc(5);
        Integer f10 = calculator.calc(10);
        assertTrue(f0 == 0 && f1 == 1 && f5 == 120 && f10 == 3628800);
    }

    @Test
    public void isModifierFinalTest() throws NoSuchFieldException {
        Field field = ReflectionTest.class.getField("testString");
        assertTrue(ReflectionTestClass.isModifierFinal(field.getModifiers()));
    }
    @Test
    public void getFinalStringsValues() throws NoSuchFieldException {
        ReflectionTest reflectionTest = new ReflectionTest();
        Field field = ReflectionTest.class.getField("testString");
        assertTrue(ReflectionTestClass.getFinalStringsFields(reflectionTest).contains(field));
    }
    @Test
    public void getAllGettersMethods() throws NoSuchMethodException {
        ReflectionTest reflectionTest = new ReflectionTest();
        Method method = ReflectionTest.class.getMethod("getTestString");
        assertTrue(ReflectionTestClass.getAllGettersMethods(reflectionTest).contains(method));
    }

}
