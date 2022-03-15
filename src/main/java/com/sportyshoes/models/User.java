package com.sportyshoes.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private int age;

    @Column(name = "date_added")
    private Date dateAdded;

    @Column(name = "emailid")
    private String emailId;

    @Column(name = "pwd")
    private String pwd;
}
