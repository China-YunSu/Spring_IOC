package com.mec.spring.demo;


import com.mec.spring.core.AutoWired;
import com.mec.spring.core.Component;

public class A {
    private B b;
    @Override
    public String toString() {
        return "AAAAAA";
    }

    public void setB(B b) {
        this.b = b;
    }
}
