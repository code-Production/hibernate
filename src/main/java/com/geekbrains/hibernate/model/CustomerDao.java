package com.geekbrains.hibernate.model;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();
    Customer findById(Long id);
    void deleteById(Long id);
    Customer saveOrUpdate(Customer customer);
    List<Product> getProductHistory(Long id);
}
