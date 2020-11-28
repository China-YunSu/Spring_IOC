package com.mec.spring.core;

import com.mec.util.PackageScan;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory extends AnnoScanner{
    private static final Map<Class<?>,BeanDefination> beanPool;

    static {
        beanPool = new HashMap<>();
    }

    static BeanDefination getBeanDefination(Class<?> arg) {
        return beanPool.get(arg);
    }

    public static void CollectionBeans(String classPath) {
        try {
            new PackageScan() {

                @Override
                protected void dealClass(Class<?> klass) {
                    dealAnnotation(klass);
                }
            }.scanPackage(classPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doMethodsList(methodTable.getMethodForInvoke());
    }

    static void addBean(Class<?> klass,Object object, boolean inject) {
        BeanDefination bean = new BeanDefination();
        bean.setInject(inject);
        bean.setKlass(klass);
        bean.setObject(object);
        beanPool.put(klass,bean);
        methodTable.refreshTable(klass);
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> parameterType) {
        BeanDefination beanDefination = beanPool.get(parameterType);

        if (beanDefination == null) {
            throw new NoSuchBeanException("Not found " + parameterType + ". maybe "
                    + parameterType + " not marked Component!");
        }
        if (!beanDefination.isInject()) {
            synchronized (beanPool) {
                if (!beanDefination.isInject()) {
                    beanDefination.setInject(true);
                    boolean res = doInject(beanDefination);
                    beanDefination.setInject(res);
                }
            }
        }
        return (T) beanDefination.getObject();
    }
}
