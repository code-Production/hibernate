package com.geekbrains.hibernate.repository;

import com.geekbrains.hibernate.model.Customer;
import com.geekbrains.hibernate.model.Order;
import com.geekbrains.hibernate.model.OrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Order findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Order order = session.createNamedQuery("Order.findById", Order.class)
                    .setParameter("id", id)
                    .getSingleResult();
//            System.out.println(customer);
            session.getTransaction().commit();
            return order;
        }
    }

    @Override
    public List<Order> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Order> orderList = session.createNamedQuery("Order.findAll", Order.class)
                    .getResultList();
            session.getTransaction().commit();
            return orderList;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Order AS o WHERE o.id = :id")//
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Order saveOrUpdate(Order order) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Order managedOrder = (Order) session.merge(order);
            session.getTransaction().commit();
            return managedOrder;
        }
    }
}
