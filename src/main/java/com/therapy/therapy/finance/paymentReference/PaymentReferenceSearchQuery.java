package com.therapy.therapy.finance.paymentReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PaymentReferenceSearchQuery {

    private int pageNumber;
    private Long serviceId;
    private String key;
    private Boolean paid;

    private Long cardNumber;
}
