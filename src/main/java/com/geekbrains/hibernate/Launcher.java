package com.geekbrains.hibernate;

import com.geekbrains.hibernate.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.geekbrains.hibernate");
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        System.out.println(customerDao.getProductHistory(2L));
        System.out.println(productDao.getCustomerHistory(2L));
    }

}
