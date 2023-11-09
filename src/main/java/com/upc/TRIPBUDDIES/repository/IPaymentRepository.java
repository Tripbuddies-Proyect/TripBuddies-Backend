package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
