package com.serheev.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

@Component
public class InjectRandomValueAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomValue annotation = field.getAnnotation(InjectRandomValue.class);
            if (annotation != null) {
                field.setAccessible(true);
                if (field.getType() == Integer.TYPE) {
                    ReflectionUtils.setField(field, bean, (int) (Math.random() * ((annotation.max() - annotation.min()) + 1) + annotation.min()));
                }
                if (field.getType() == String.class) {
                    ReflectionUtils.setField(field, bean, annotation.text());
                }
                if (field.getType() == Boolean.TYPE) {
                    ReflectionUtils.setField(field, bean, annotation.flag());
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
