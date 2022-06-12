package com.gg.duonduo.domain;

import lombok.Data;

@Data
public class User {

    int id;
    String username;
    String password;
    String firstName;
    String lastName;
    int age;
    int salary;

}