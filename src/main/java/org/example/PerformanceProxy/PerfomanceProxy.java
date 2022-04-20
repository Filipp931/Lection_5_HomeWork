package org.example.PerformanceProxy;

import org.example.Calculator.Calculator;

import java.lang.reflect.Proxy;

public class PerfomanceProxy implements Calculator{
    private Calculator calculator;
    public PerfomanceProxy (Calculator calculator) {
        this.calculator = (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(),
                calculator.getClass().getInterfaces(),
                new GetTimeHandler(calculator));
    }

    @Override
    public int calc(int number) {
        return calculator.calc(number);
    }
}
