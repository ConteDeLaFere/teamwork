package com.malina.teamwork.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ProductType type;
    private String name;

    public Product(ProductType type, String name) {
        this.type = type;
        this.name = name;
    }

    public enum ProductType {
        CREDIT, INVEST, SAVING, DEBIT
    }
}
