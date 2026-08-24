package com.app.ecom.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> items;


    private Double totalAmount;
}
