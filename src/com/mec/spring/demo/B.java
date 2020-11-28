package com.mec.spring.demo;


import com.mec.spring.core.AutoWired;
import com.mec.spring.core.Component;

public class B {
    private SomeClass someClass;
    @Override
    public String toString() {
        return "BBBBB";
    }

    public void setSomeClass(SomeClass someClass) {
        this.someClass = someClass;
    }
}
