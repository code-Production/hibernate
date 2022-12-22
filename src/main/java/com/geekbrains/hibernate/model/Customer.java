package com.geekbrains.hibernate.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "orders")
@NoArgsConstructor

@Entity
@Table (schema = "spring_db", name = "customers")
@NamedQueries({
        @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer AS c WHERE c.id = :id"),
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer AS c")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
