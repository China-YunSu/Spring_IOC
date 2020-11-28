package com.mec.spring.demo;

import com.google.gson.Gson;
import com.mec.spring.core.AutoWired;
import com.mec.spring.core.Component;

@Component
public class SomeClass {
    @AutoWired
    private People people;
    @AutoWired
    private Complex complex;
    @AutoWired
    private Gson gson;
    @AutoWired
    private A a;

    public SomeClass() {
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    @Override
    public String toString() {
        return gson.toJson(people) + " " + gson.toJson(complex);
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void setA(A a) {
        this.a = a;
    }
}
