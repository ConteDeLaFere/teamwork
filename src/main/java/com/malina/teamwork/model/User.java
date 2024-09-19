package com.malina.teamwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime date;
    private String userName;
    private String firstName;
    private String lastName;

    public User(LocalDateTime date, String userName, String firstName, String lastName) {
        this.date = date;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
