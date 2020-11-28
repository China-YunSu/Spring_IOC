package com.mec.spring.demo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mec.spring.core.Bean;
import com.mec.spring.core.Component;

@Component
public class Config {

    @Bean
    public Gson getGson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    @Bean
    public A getA(B b) {
        return new A();
    }

    @Bean
    public B getB(C C) {
        return new B();
    }

    @Bean
    public D getB(B b,A a) {
        return new D();
    }

    @Bean
    public C get() {
        return new C();
    }
}
