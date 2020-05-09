package com.dz.student.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "phnNo")
    private String phnNo;

    @Column(name = "address")
    private String address;

}
