package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.CachingProxy.CachingHandler;
import org.example.Calculator.Calculator;
import org.example.Calculator.CalculatorImpl;
import org.example.TestClasses.From;
import org.example.TestClasses.To;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
    /**
     * ==============================================================
     * BeansUtils test
     * ==============================================================
     */
    @Test
    public void assignTest(){
    From from = new From(213,654,90,"From");
        System.out.println("FROM"+from.toString());
    To to = new To(564, 2,4,false);
        System.out.println( " TO " + to.toString());
    BeansUtils.assign(to,from);
        System.out.println("===================================");
        System.out.println("after assign");
        System.out.println("FROM"+from.toString());
        System.out.println( " TO " + to.toString());

}

    @Test
    public void getCorrectClassTypeTest(){
        Class integer = int.class;
        Class bool = boolean.class;
        assertTrue(BeansUtils.getCorrectClassType(integer) == Integer.class &&
                BeansUtils.getCorrectClassType(bool) == Boolean.class);
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
    /**
     * ==============================================================
     * Calculator tests
     * ==============================================================
     */
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

    /**
     * ==============================================================
     * CachingProxy tests
     * ==============================================================
     */
    @Test
    public void CachingProxyTest(){
        Calculator calculator = new CalculatorImpl();
        Calculator cashedCalculator = (Calculator) Proxy.newProxyInstance(
                calculator.getClass().getClassLoader(),
                calculator.getClass().getInterfaces(),
                new CachingHandler(calculator));
        System.out.println("start");
        int fact10cashed = cashedCalculator.calc(10);
        int fact10 = calculator.calc(10);
        fact10cashed = cashedCalculator.calc(10);
        assertEquals(fact10, fact10cashed);
}




}
