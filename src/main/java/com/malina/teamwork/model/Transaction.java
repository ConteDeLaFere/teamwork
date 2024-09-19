package com.malina.teamwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private UUID userId;
    private UUID productId;
    private long amount;

    public Transaction(TransactionType type, UUID userId, UUID productId, long amount) {
        this.type = type;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
}
