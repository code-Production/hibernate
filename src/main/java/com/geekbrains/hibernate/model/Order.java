package com.geekbrains.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(schema = "spring_db", name = "orders", indexes = {
        @Index(name = "customer_id_idx",columnList = "customer_id"),
        @Index(name = "product_id_idx", columnList = "product_id")
})
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order AS o"),
        @NamedQuery(name = "Order.findById", query = "SELECT o FROM Order AS o WHERE o.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date date;

    @Column(name = "price")
    private double price;

}
