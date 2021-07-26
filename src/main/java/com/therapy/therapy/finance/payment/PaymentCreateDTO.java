package com.therapy.therapy.finance.payment;

import com.therapy.therapy.finance.paymentReference.PaymentReference;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PaymentCreateDTO {
    private Long   referenceId;
    private Double amount;
    private String payerFullName;
    private String payerTin;
    private PAYMENT_METHOD paymentMethod;
    private String receiptNumber;
    private String paymentNote;

    public static Payment toPayment(PaymentCreateDTO dto, PaymentReference reference, Staff staff){

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPayerFullName(dto.getPayerFullName());
        payment.setPayerTin(dto.getPayerTin());
        payment.setDate(new Date());
        payment.setPaymentNote(dto.getPaymentNote());
        payment.setReceiptNumber(dto.getReceiptNumber());
        payment.setPaymentNote(dto.getPaymentNote());

        if(reference!=null)
            payment.setPaymentReference(reference);
        if(staff!=null)
            payment.setStaff(staff);

        return payment;
    }
}
