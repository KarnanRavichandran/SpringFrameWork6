package com.timothy.Init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@Lazy
class ClassA{
    public ClassA() {
        log.info("classA");
    }
    public void message(){
        System.out.println("messageA");
    }
}
@Component
@Slf4j
@Lazy
class ClassB{
    ClassA classA;

    @Autowired
    public ClassB(ClassA classA) {
        this.classA = classA;
        log.info("classB");
        log.info("DependencyLoaded - msg from classB");
    }
    public void message(){
        System.out.println("messageB");
    }
}
@Configuration
@ComponentScan
@Slf4j
@SpringBootApplication
public class TestingEagerAndLazyInit {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TestingEagerAndLazyInit.class);
        log.info("ContextLoaded");

        context.getBean(ClassB.class);
        context.getBean(ClassB.class).message();
//        SpringApplication.run(TestingEagerAndLazyInit.class, args);

    }
}
