package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.CachingProxy.CachingHandler;
import org.example.Calculator.Calculator;
import org.example.Calculator.CalculatorImpl;
import org.example.PerformanceProxy.GetTimeHandler;
import org.example.PerformanceProxy.PerfomanceProxy;
import org.example.TestClasses.From;
import org.example.TestClasses.To;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Unit test for ReflectionTestClass.
 */
public class Test
{
    public final String testString = "testString";

    public String getTestString() {
        return testString;
    }

    /**
     * ==============================================================
     * BeansUtils test
     * ==============================================================
     */
    @org.junit.Test
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

    @org.junit.Test
    public void getCorrectClassTypeTest(){
        Class integer = int.class;
        Class bool = boolean.class;
        assertTrue(BeansUtils.getCorrectClassType(integer) == Integer.class &&
                BeansUtils.getCorrectClassType(bool) == Boolean.class);
    }

    @org.junit.Test
    public void isModifierFinalTest() throws NoSuchFieldException {
        Field field = Test.class.getField("testString");
        assertTrue(ReflectionTestClass.isModifierFinal(field.getModifiers()));
    }
    @org.junit.Test
    public void getFinalStringsValues() throws NoSuchFieldException {
        Test test = new Test();
        Field field = Test.class.getField("testString");
        assertTrue(ReflectionTestClass.getFinalStringsFields(test).contains(field));
    }
    @org.junit.Test
    public void getAllGettersMethods() throws NoSuchMethodException {
        Test test = new Test();
        Method method = Test.class.getMethod("getTestString");
        assertTrue(ReflectionTestClass.getAllGettersMethods(test).contains(method));
    }
    /**
     * ==============================================================
     * Calculator tests
     * ==============================================================
     */
    @org.junit.Test
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
    @org.junit.Test
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
    /**
     * ==============================================================
     * PerfomanceProxy tests
     * ==============================================================
     */
    @org.junit.Test
    public void PerfomanceProxyTest() {
        Calculator calculator = new CalculatorImpl();
        Calculator calculator1 = new PerfomanceProxy(calculator);
        System.out.println(calculator1.calc(3));

            }

}
