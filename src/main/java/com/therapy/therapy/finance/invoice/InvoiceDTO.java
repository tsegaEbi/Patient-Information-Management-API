package com.therapy.therapy.finance.invoice;

import com.therapy.therapy.finance.INVOICE_FOR_CATEGORY;
import com.therapy.therapy.finance.item.Item;
import com.therapy.therapy.finance.item.ItemDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter

public class InvoiceDTO {

    private INVOICE_FOR_CATEGORY invoiceCategory;
    private Long referenceId;

    private Long customerId;

    private String customerTin;

    private String customerAddress;

    private String customerFullName;

    private ItemDTO item;

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

    private Long preparedBy;

    public static Invoice toInvoice(InvoiceDTO dto, Item item, Long preparedBy){
        Invoice invoice = new Invoice();

        invoice.setVatIncluded(dto.getVatIncluded());
        invoice.setItem(item);
        invoice.setQuantity(dto.getQuantity());
        invoice.setInvoiceCategory(dto.getInvoiceCategory());
        invoice.setCustomerAddress(dto.getCustomerAddress());
        invoice.setCustomerFullName(dto.getCustomerFullName());
        invoice.setCustomerTin(dto.getCustomerTin());
        invoice.setReferenceId(dto.getReferenceId());

        invoice.setDeductedDiscount(dto.getDeductedDiscount());
        invoice.setDeductedDiscountTitle(dto.getDeductedDiscountTitle());

        invoice.setAddedOtherCharges(dto.getAddedOtherCharges());
        invoice.setAddedServiceCharge(dto.getAddedServiceCharge());

        invoice.setAddedTax1(dto.getAddedTax1());
        invoice.setAddedTax1Title(dto.getAddedTax1Title());

        invoice.setAddedTax2(dto.getAddedTax2());
        invoice.setAddedTax2Title(dto.getAddedTax2Title());

        invoice.setAddedTax3(dto.getAddedTax3());
        invoice.setAddedTax3Title(dto.getAddedTax3Title());

        return invoice;

    }
}
