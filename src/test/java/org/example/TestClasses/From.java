package org.example.TestClasses;

public class From {
    private int a;
    private int b;
    private int notInTo;
    private String differentType;

    public From(int a, int b, int notInTo, String differentType) {
        this.a = a;
        this.b = b;
        this.notInTo = notInTo;
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

    public int getNotInTo() {
        return notInTo;
    }

    public void setNotInTo(int notInTo) {
        this.notInTo = notInTo;
    }

    public String getDifferentType() {
        return differentType;
    }

    public void setDifferentType(String differentType) {
        this.differentType = differentType;
    }

    @Override
    public String toString() {
        return new String(this.getClass().getName()+" { a = "+a+"; b = "+b+"; notInTo = " + notInTo + "; differentType = " + differentType+";}");
    }
}
