package com.dz.order.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "bookId")
    private String bookId;

    @Column(name = "bookQuantity")
    private Integer bookQuantity;

    @Column(name = "orderDate")
    private String orderDate;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "userReturnDate")
    private String userReturnDate;

    @Column(name = "delay")
    private String delay;
}
