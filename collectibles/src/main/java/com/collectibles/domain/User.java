package com.collectibles.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id", unique=true)
    private Long id;

    @Column(name="name", unique=true)
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}