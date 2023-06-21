package com.example.backend.model;

import com.example.backend.controller.Crypto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "emial")
    private String email;

    @Column(name = "password")
    private String passwordHash;

    @Column(name = "role")
    private String role = "User";

    @Column(name = "created_timestamp")
    private Date createDate;

    @Column(name = "updated_timestamp")
    private Date updateDate;

    public void setPassword(String password) throws Exception {
        Crypto crypto = new Crypto();
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] hashedPassword = md.digest(password.getBytes());
        this.passwordHash = crypto.encrypt(password);
    }

//    public String getPassword(String password) throws Exception {
//        Crypto crypto = new Crypto();
//        String decryptPassword;
////        MessageDigest md = MessageDigest.getInstance("SHA-256");
////        byte[] hashedPassword = md.digest(password.getBytes());
//        decryptPassword = crypto.decrypt(password);
//        return decryptPassword;
//    }

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }
}
