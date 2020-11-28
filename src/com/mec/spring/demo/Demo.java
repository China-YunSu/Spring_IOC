package com.mec.spring.demo;

import com.mec.spring.core.BeanFactory;

public class Demo {

    public static void main(String[] args) {
        BeanFactory.CollectionBeans("com.mec.spring.demo");

        SomeClass someClass = BeanFactory.getBean(SomeClass.class);
        System.out.println(someClass);
        System.out.println(someClass.getComplex());
        System.out.println(someClass.getPeople());

        D bean = BeanFactory.getBean(D.class);
        System.out.println(bean);

    }
}
