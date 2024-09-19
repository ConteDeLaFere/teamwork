package com.malina.teamwork.repository;

import static com.malina.teamwork.model.Product.ProductType;
import static com.malina.teamwork.model.Transaction.TransactionType;

import com.malina.teamwork.model.User;
import com.malina.teamwork.repository.projection.PriceSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) as count FROM user_to_product utp " +
                    "JOIN products p ON utp.product_id = p.id " +
                    "WHERE user_id = :userId AND type = :productType"
    )
    Integer countProducts(UUID userId, ProductType productType);
    
    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) as count FROM transactions t " +
                    "JOIN products p ON t.product_id = p.id " +
                    "WHERE user_id = :userId AND p.type = :productType " +
                    "AND t.type = :transactionType AND amount = :amount"
    )
    Integer countOperations(UUID userId, ProductType productType, TransactionType transactionType, long amount);

    @Query(
            nativeQuery = true,
            value = "SELECT t.product_id as productId, SUM(t.amount) as amount FROM transactions t " +
                    "JOIN products p ON t.product_id = p.id " +
                    "WHERE user_id = :userId AND p.type = :productType AND t.type = :transactionType " +
                    "GROUP BY t.product_id "
    )
    List<PriceSum> calculateSumByProduct(UUID userId, ProductType productType, TransactionType transactionType);

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(t.amount) FROM transactions t " +
                    "JOIN products p ON t.product_id = p.id " +
                    "WHERE user_id = :userId AND p.type = :productType AND t.type = :transactionType "
    )
    Long calculateSum(UUID userId, ProductType productType, TransactionType transactionType);
}
