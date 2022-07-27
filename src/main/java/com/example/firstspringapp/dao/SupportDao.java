package com.example.firstspringapp.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "support")
public class SupportDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_title")
    private String orderTitle;

    @Column(name = "order_description")
    private String orderDescription;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerDao orderDao;

}
