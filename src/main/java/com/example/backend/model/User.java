package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "emial")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private int role;

    @Column(name = "create_date")
    private String create_date;

    @Column(name = "update_date")
    private String update_date;

}