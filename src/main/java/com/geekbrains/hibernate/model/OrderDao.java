package com.geekbrains.hibernate.model;

import java.util.List;

public interface OrderDao {

    List<Order> findAll();
    Order findById(Long id);
    void deleteById(Long id);
    Order saveOrUpdate(Order order);
}
