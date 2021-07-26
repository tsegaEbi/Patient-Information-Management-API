package com.therapy.therapy.finance.payment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.finance.paymentReference.PaymentReference;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceDTO;

import java.util.List;

public interface PaymentService extends CRUDService<Payment> {

    List<Payment> getByPatientId(Long id);

    List<Payment>getByReference(PaymentReference ref);


    ActionResponse<Payment> create(Payment payment, PaymentReferenceDTO dto);

}
