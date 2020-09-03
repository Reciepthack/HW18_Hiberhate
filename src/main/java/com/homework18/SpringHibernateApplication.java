package com.homework18;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class SpringHibernateApplication {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpringHibernateApplication.class);
    }
}
