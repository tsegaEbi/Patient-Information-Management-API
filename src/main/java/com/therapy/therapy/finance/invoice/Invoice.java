package com.therapy.therapy.finance.invoice;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.finance.INVOICE_FOR_CATEGORY;
import com.therapy.therapy.finance.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="invoice")
public class Invoice extends Model {

    @Enumerated(EnumType.STRING)
    private INVOICE_FOR_CATEGORY invoiceCategory;

    private Long referenceId;

    private Long customerId;

    private String customerTin;

    private String customerAddress;

    private String customerFullName;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    private Double quantity;

    private Double deductedDiscount;

    private String deductedDiscountTitle;

    private Double  addedServiceCharge;

    private Double  addedOtherCharges;

    private String  addOtherChargesTitle;

    private Double  addedTax1;
    private String  addedTax1Title;

    private Double  addedTax2;
    private String  addedTax2Title;

    private Double  addedTax3;
    private String  addedTax3Title;

    private Boolean vatIncluded;

    private Double vatCalculated;

    private Double invoiceGenerated;

    private Date generatedDate;

    private Double netDue;

    private Long preparedBy;
}
