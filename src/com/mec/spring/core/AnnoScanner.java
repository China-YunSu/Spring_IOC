package com.mec.spring.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

 class AnnoScanner {
    protected static final ScanTable methodTable = new ScanTable();

    static void dealAnnotation(Class<?> klass) {
        if (!klass.isAnnotationPresent(Component.class)) {
            return;
        }

        try {
            Object object = klass.newInstance();
            BeanFactory.addBean(klass,object,false);

            dealMethodsAnno(klass,object);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void dealMethodsAnno(Class<?> klass, Object object) {
        try {
            for (Method method : klass.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Bean.class)) {
                    continue;
                }
                Class<?>[] types = method.getParameterTypes();
                if (types.length <= 0) {
                    Object beanValue = method.invoke(object);
                    BeanFactory.addBean(beanValue.getClass(),beanValue,true);
                } else {
                    methodTable.addMethod(method,object);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

     static void doMethodsList(List<MethodDefination> methodDefinations) {
        if (methodDefinations == null) {
            if (!methodTable.isEmpty()) {
                throw new CircleGetBeanException(methodTable.showErrorMethods());
            }
            return;
        }

        try {
            for (MethodDefination methodDefination : methodDefinations) {
                Method method = methodDefination.getMethod();
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object[] values = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    values[i] = BeanFactory.getBean(parameterTypes[i]);
                }
                Object bean = method.invoke(methodDefination.getObject(), values);
                BeanFactory.addBean(bean.getClass(),bean,true);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
         doMethodsList(methodTable.getMethodForInvoke());
    }

     static boolean doInject(BeanDefination beanDefination) {
        Object object = beanDefination.getObject();
        Class<?> klass = beanDefination.getKlass();
        try {
            for (Field field : klass.getDeclaredFields()) {
                if (!field.isAnnotationPresent(AutoWired.class)) {
                    continue;
                }
                Object bean = BeanFactory.getBean(field.getType());
                String methodName = "set" + field.getName().substring(0, 1).toUpperCase() +
                        field.getName().substring(1);
                Method method = klass.getDeclaredMethod(methodName, field.getType());
                method.invoke(object,bean);
            }
            return true;
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
         return false;
    }
}
