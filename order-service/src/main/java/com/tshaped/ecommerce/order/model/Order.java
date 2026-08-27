package com.tshaped.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            unique = true)
    private String orderNumber;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderLineItem> orderLineItems;

}
