package com.geekbrains.hibernate.repository;

import com.geekbrains.hibernate.model.Customer;
import com.geekbrains.hibernate.model.CustomerDao;
import com.geekbrains.hibernate.model.Order;
import com.geekbrains.hibernate.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Customer findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createNamedQuery("Customer.findById", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
//            System.out.println(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customerList = session.createNamedQuery("Customer.findAll", Customer.class)
                    .getResultList();
            session.getTransaction().commit();
            return customerList;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Customer AS c WHERE c.id = :id")//
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer managedCustomer = (Customer) session.merge(customer);
            session.getTransaction().commit();
            return managedCustomer;
        }
    }

    @Override
    public List<Product> getProductHistory(Long id) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> history = session.get(Customer.class, id).getOrders().stream()
                    .map(Order::getProduct)
                    .toList();
            session.getTransaction().commit();
            return history;
        }
    }
}
