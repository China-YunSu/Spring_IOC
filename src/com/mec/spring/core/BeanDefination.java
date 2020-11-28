package com.mec.spring.core;

public class BeanDefination {
    private Class<?> klass;
    private Object object;
    private boolean inject;

    public Class<?> getKlass() {
        return klass;
    }

    public void setKlass(Class<?> klass) {
        this.klass = klass;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isInject() {
        return inject;
    }

    public void setInject(boolean inject) {
        this.inject = inject;
    }
}
