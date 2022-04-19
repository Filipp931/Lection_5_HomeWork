package org.example.TestClasses;

public class To {
    private int a;
    private int b;
    private int notInFrom;
    private boolean differentType;

    public To(int a, int b, int notInFrom, boolean differentType) {
        this.a = a;
        this.b = b;
        this.notInFrom = notInFrom;
        this.differentType = differentType;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getNotInFrom() {
        return notInFrom;
    }

    public void setNotInFrom(int notInFrom) {
        this.notInFrom = notInFrom;
    }

    public boolean isDifferentType() {
        return differentType;
    }

    public void setDifferentType(boolean differentType) {
        this.differentType = differentType;
    }
    @Override
    public String toString() {
        return new String(this.getClass().getName()+"a="+a+" b="+b+" notInFrom = " + notInFrom + " differentType =" + differentType);
    }
}
