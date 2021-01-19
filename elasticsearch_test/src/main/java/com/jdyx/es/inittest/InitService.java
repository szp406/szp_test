package com.jdyx.es.inittest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//ggggggggghhhhhhhhhhhhhhh
//setBeanName->setBeanClassLoader->setBeanFactory->setApplicationContext->postConstructMethod->afterPropertiesSet
// ->postProcessBeforeInitialization->postProcessAfterInitialization->ApplicationRunner->commandlineRunner
@Component
public class InitService implements ApplicationRunner, CommandLineRunner, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware,
        InitializingBean, BeanPostProcessor, DisposableBean {
    //来自注解@ApplicationRunner
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner");
    }

    //来自注解@CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        System.out.println("commandlineRunner");
    }

    //来自BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("invoke setBeanName:    " + name);
    }

    //来自BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("02-->BeanFactoryAware接口被调用了    "+ beanFactory.toString());
    }

    //来自BeanClassLoaderAware
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("invoke setBeanClassLoader");
    }

    //来自ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("invoke setApplicationContext");
    }

    //来自注解@PostConstruct
    @PostConstruct
    public void postConstructMethod() {
        System.out.println("invoke postConstructMethod");
    }

    //来自InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("invoke afterPropertiesSet");
    }

    //来自配置initMethod = "initMethod"
    public void initMethod() {
        System.out.println("invoke init-method");
    }

    //来自BeanPostProcessor
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("invoke postProcessBeforeInitialization= "+beanName);
        return bean;
    }

    //来自BeanPostProcessor
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("invoke postProcessAfterInitialization= "+beanName);
        return bean;
    }

    //bean使用（如此时调用了下面的use方法）
    public void use() {
        System.out.println("use bean");
    }

    //来自注解@PreDestroy
    @PreDestroy
    public void preDestroyMethod() {
        System.out.println("invoke preDestroyMethod");
    }

    //来自bean定义中的配置destroyMethod = "destoryMethod"
    public void destoryMethod() {
        System.out.println("invoke destory-method");
    }

    //来自DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("invoke destroy");
    }
}
