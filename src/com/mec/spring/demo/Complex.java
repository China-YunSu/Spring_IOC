package com.mec.spring.demo;

import com.mec.spring.core.Component;

@Component
public class Complex {
    private double real;
    private double vir;

    public Complex() {
        this.real = 2.1;
        this.vir = 2.2;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getVir() {
        return vir;
    }

    public void setVir(double vir) {
        this.vir = vir;
    }

    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real +
                ", vir=" + vir +
                '}';
    }
}
