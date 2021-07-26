package com.therapy.therapy.finance.payment;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.finance.paymentReference.PaymentReference;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="payment")
public class Payment extends Model {

    @ManyToOne
    @JoinColumn(name="staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="payment_reference_id")
    private PaymentReference paymentReference;


    private Date date= new Date();
    private String payerFullName;


    private String payerTin;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PAYMENT_METHOD paymentMethod;

    private String receiptNumber;

    private String paymentNote;
}
