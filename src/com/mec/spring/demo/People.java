package com.mec.spring.demo;

import com.mec.spring.core.Component;

@Component
public class People {
    private String name;
    private String hight;

    public People() {
        this.name = "李柯柯";
        this.hight = "1.70";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", hight='" + hight + '\'' +
                '}';
    }
}
