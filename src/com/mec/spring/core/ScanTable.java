package com.mec.spring.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 class ScanTable {
    private final Map<MethodDefination, Map<Class<?>,Object>> methodPool;
    private final Map<Class<?>, List<MethodDefination>> argPool;

     ScanTable() {
        methodPool = new HashMap<>();
        argPool = new HashMap<>();
    }

      void addMethod(Method method,Object object) {
        MethodDefination methodDefination = new MethodDefination(method, object);

        Map<Class<?>,Object> argMap = new HashMap<>();
        for (Class<?> parameterType : method.getParameterTypes()) {
            if (BeanFactory.getBeanDefination(parameterType) != null) {
                continue;
            }
            argMap.put(parameterType,null);

            List<MethodDefination> methodDefinations = argPool.computeIfAbsent(parameterType, k -> new ArrayList<>());
            methodDefinations.add(methodDefination);
        }

        methodPool.put(methodDefination,argMap);
    }


     void refreshTable(Class<?> klass) {
        if (methodPool.isEmpty()) {
            return;
        }

        List<MethodDefination> methodDefinations = argPool.remove(klass);
        if (methodDefinations == null) {
            return;
        }

        for (MethodDefination methodDefination : methodDefinations) {
            Map<Class<?>, Object> methodArgs = methodPool.get(methodDefination);
            methodArgs.remove(klass);
        }
    }

     List<MethodDefination> getMethodForInvoke() {
        if (methodPool.isEmpty()) {
            return null;
        }

        List<MethodDefination> methods  = new ArrayList<>();

        for (MethodDefination methodDefination : methodPool.keySet()) {
            Map<Class<?>, Object> args = methodPool.get(methodDefination);
            if (args.isEmpty()) {
                methods.add(methodDefination);
            }
        }

        for (MethodDefination method : methods) {
            methodPool.remove(method);
        }

        return methods.isEmpty() ? null : methods;
    }

     boolean isEmpty() {
        return methodPool.isEmpty();
    }

     String showErrorMethods() {
        StringBuilder message = new StringBuilder("Found Error Methods: \n");


        for (MethodDefination methodDefination : methodPool.keySet()) {
            boolean first = true;
            Method method = methodDefination.getMethod();
            message.append("\t\t").append(method.getReturnType().getName())
                    .append(" ").append(method.getName()).append("(");
            for (Class<?> parameterType : methodDefination.getMethod().getParameterTypes()) {
                message.append(first ? "":", ").append(parameterType.getName());
                first = false;
            }
            message.append(")\n");
        }

        return message.toString();
    }
}
