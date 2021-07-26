package com.therapy.therapy.finance.payment;

import com.therapy.therapy.finance.paymentReference.PaymentReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByPaymentReference(PaymentReference paymentReference);
}
