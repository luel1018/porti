package org.example.porti.orders;

import org.example.porti.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    boolean existsByPgPaymentId(String pgPaymentId);
}