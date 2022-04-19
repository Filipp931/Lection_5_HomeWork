package org.example;

public class CalculatorImpl implements Calculator{
    public static final String MONDAY = "MONDAY";
    public static final String CALCULATOR = "CALCULATOR";
    public String testString;
    public static String staticTestString = "staticTestString";
    /**
     * расчет факториала переданного числа
     * @param number
     * @return int факториал
     */

    @Override
    public int calc(int number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        return number * calc(number - 1);
    }

    public static String getMONDAY() {
        return MONDAY;
    }

    public static String getCALCULATOR() {
        return CALCULATOR;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public static void setStaticTestString(String staticTestString) {
        CalculatorImpl.staticTestString = staticTestString;
    }
}
